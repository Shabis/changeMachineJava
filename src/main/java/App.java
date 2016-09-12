import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/change", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      ChangeMachine changeMachine = new ChangeMachine();
      float cash = Float.parseFloat(request.queryParams("inputCash"));
      String outputString = (cash <= 8.20f) ? changeMachine.makeChange(cash):"Not enough change in the machine! Please input a smaller number.";
      model.put("changeString", outputString);
      model.put("template", "templates/change.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
}
