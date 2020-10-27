package org.springframework.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FrameworkServlet;

public class DelegatingFilterProxy extends GenericFilterBean {

    private String contextAttribute;

    private WebApplicationContext webApplicationContext;

    private String targetBeanName;

    private boolean targetFilterLifecycle = false;

    private volatile Filter delegate;

    private final Object delegateMonitor = new Object();


    /**
     * Create a new {@code DelegatingFilterProxy}. For traditional (pre-Servlet 3.0) use
     * in {@code web.xml}.
     *
     * @see #setTargetBeanName(String)
     */
    public DelegatingFilterProxy() {
    }


    public DelegatingFilterProxy(Filter delegate) {
        Assert.notNull(delegate, "Delegate Filter must not be null");
        this.delegate = delegate;
    }

    public DelegatingFilterProxy(String targetBeanName) {
        this(targetBeanName, null);
    }

    public DelegatingFilterProxy(String targetBeanName, WebApplicationContext wac) {
        Assert.hasText(targetBeanName, "Target Filter bean name must not be null or empty");
        this.setTargetBeanName(targetBeanName);
        this.webApplicationContext = wac;
        if (wac != null) {
            this.setEnvironment(wac.getEnvironment());
        }
    }

    public void setContextAttribute(String contextAttribute) {
        this.contextAttribute = contextAttribute;
    }


    public String getContextAttribute() {
        return this.contextAttribute;
    }


    public void setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

    protected String getTargetBeanName() {
        return this.targetBeanName;
    }


    public void setTargetFilterLifecycle(boolean targetFilterLifecycle) {
        this.targetFilterLifecycle = targetFilterLifecycle;
    }


    protected boolean isTargetFilterLifecycle() {
        return this.targetFilterLifecycle;
    }


    @Override
    protected void initFilterBean() throws ServletException {
        synchronized (this.delegateMonitor) {
            if (this.delegate == null) {
                // If no target bean name specified, use filter name.
                if (this.targetBeanName == null) {
                    this.targetBeanName = getFilterName();
                }
                // Fetch Spring root application context and initialize the delegate early,
                // if possible. If the root application context will be started after this
                // filter proxy, we'll have to resort to lazy initialization.
				/*WebApplicationContext wac = findWebApplicationContext();
				if (wac != null) {
					this.delegate = initDelegate(wac);
				}*/
            }
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
// Lazily initialize the delegate if necessary.

        Filter delegateToUse = this.delegate;
        if (delegateToUse == null) {
            synchronized (this.delegateMonitor) {
                delegateToUse = this.delegate;
                if (delegateToUse == null) {
                    // 把原来的查找 IOC 容器的代码注释掉
                    // WebApplicationContext wac = findWebApplicationContext();
                    // 按我们自己的需要重新编写
                    // 1.获取 ServletContext 对象
                    ServletContext sc = this.getServletContext();
                    // 2.拼接 SpringMVC 将 IOC 容器存入 ServletContext 域的时候使用的属性名
                    String servletName = "springDispatcherServlet";
                    String attrName = FrameworkServlet.SERVLET_CONTEXT_PREFIX + servletName;
                    // 3.根据 attrName 从 ServletContext 域中获取 IOC 容器对象
                    WebApplicationContext wac = (WebApplicationContext) sc.getAttribute(attrName);
                    if (wac == null) {
                        throw new IllegalStateException("No WebApplicationContext found: " +
                                "no ContextLoaderListener or DispatcherServlet registered?");
                    }
                    delegateToUse = initDelegate(wac);
                }
                this.delegate = delegateToUse;
            }
        }
        invokeDelegate(delegateToUse, request, response, filterChain);
    }


    @Override
    public void destroy() {
        Filter delegateToUse = this.delegate;
        if (delegateToUse != null) {
            destroyDelegate(delegateToUse);
        }
    }


    protected WebApplicationContext findWebApplicationContext() {
        if (this.webApplicationContext != null) {
            // The user has injected a context at construction time -> use it...
            if (this.webApplicationContext instanceof ConfigurableApplicationContext) {
                ConfigurableApplicationContext cac = (ConfigurableApplicationContext) this.webApplicationContext;
                if (!cac.isActive()) {
                    // The context has not yet been refreshed -> do so before returning it...
                    cac.refresh();
                }
            }
            return this.webApplicationContext;
        }
        String attrName = getContextAttribute();
        if (attrName != null) {
            return WebApplicationContextUtils.getWebApplicationContext(getServletContext(), attrName);
        } else {
            return WebApplicationContextUtils.findWebApplicationContext(getServletContext());
        }
    }

    protected Filter initDelegate(WebApplicationContext wac) throws ServletException {
        Filter delegate = wac.getBean(getTargetBeanName(), Filter.class);
        if (isTargetFilterLifecycle()) {
            delegate.init(getFilterConfig());
        }
        return delegate;
    }


    protected void invokeDelegate(
            Filter delegate, ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        delegate.doFilter(request, response, filterChain);
    }


    protected void destroyDelegate(Filter delegate) {
        if (isTargetFilterLifecycle()) {
            delegate.destroy();
        }
    }

}
