package de.chkal.rewrite.test;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ocpsoft.rewrite.bind.El;
import com.ocpsoft.rewrite.config.Configuration;
import com.ocpsoft.rewrite.config.ConfigurationBuilder;
import com.ocpsoft.rewrite.config.Direction;
import com.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import com.ocpsoft.rewrite.servlet.config.Path;
import com.ocpsoft.rewrite.servlet.config.Redirect;

public class RewriteConfig extends HttpConfigurationProvider
{

    @Autowired
    private SomeBean bean;

    @Override
    public Configuration getConfiguration(ServletContext context)
    {

        String m = bean != null ? bean.getMessage() : "null";
        System.out.println("-----##------> "+m);

        return ConfigurationBuilder.begin()
//                .defineRule()
//                .when(Direction.isInbound().and(Path.matches("/test")))
//                .perform(Redirect.temporary("test.html?time="+System.currentTimeMillis()))

                .defineRule()
                .when(Direction.isInbound().and(Path.matches("/value/{value}")
                        .where("value").bindsTo(El.property("someBean.value"))))
                .perform(Redirect.temporary(context.getContextPath()+"/got-{value}")
                        .where("value").bindsTo(El.property("someBean.value")));

                
//                .defineRule()
//                .when(Direction.isInbound().and(Path.matches("/current")))
//                .perform(Redirect.temporary("user-{systemProperty['user.name']}")); // 

    }

    @Override
    public int priority()
    {
        return 0;
    }

}
