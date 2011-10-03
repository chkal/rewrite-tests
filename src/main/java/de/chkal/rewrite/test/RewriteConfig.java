package de.chkal.rewrite.test;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;

import com.ocpsoft.rewrite.config.Configuration;
import com.ocpsoft.rewrite.config.ConfigurationBuilder;
import com.ocpsoft.rewrite.config.Direction;
import com.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import com.ocpsoft.rewrite.servlet.config.Path;
import com.ocpsoft.rewrite.servlet.config.Redirect;

@Named
@ApplicationScoped
public class RewriteConfig extends HttpConfigurationProvider
{

   @Override
   public Configuration getConfiguration(ServletContext context)
   {
      return ConfigurationBuilder.begin()
               .defineRule()
               .when(Direction.isInbound().and(Path.matches("/test")))
               .perform(Redirect.temporary("test.html?"+System.currentTimeMillis()));
   }

   @Override
   public int priority()
   {
      return 0;
   }

}
