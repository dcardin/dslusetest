package test;

import com.bc360.configurator.property.impl.ValueProperty;

@SuppressWarnings("all")
public class Test {
  private final ValueProperty<String> c = new ValueProperty<String>("c");
  
  private static class Values {
    private static String init_c_1 = "true";
    
    private static String init_c_2 = "bonjour toto";
  }
  
  public void init() {
    c.setAttribute(VISIBLE,Values.init_c_1);
    c.setAttribute(LABEL,Values.init_c_2);
  }
}
