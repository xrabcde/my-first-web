package net.slipp;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class HelloWorld {
    public static void main(String[] args) {
        staticFiles.location("/static"); // Static files
//        get("/hello/:name", (req, res) -> {
//            return "Hello : " + req.params(":name");
//        });

        get("/hello", (req, res) -> {
            return "Get Hello : " + req.queryParams("name") + " 나이는 : " + req.queryParams("age");
        });
//        post("/hello", (req, res) -> {
//            return "Post Hello : " + req.queryParams("name") + " 나이는 : " + req.queryParams("age");
//        });

//        post("/hello", (req, res) -> {
//            return "<html>" +
//                    "<body>" +
//                    "<h1>회원 가입 결과</h1>" +
//                    "이름 : " + req.queryParams("name") +
//                    "<br /><br />" +
//                    "나이 : " + req.queryParams("age") +
//                    "</body>" +
//                    "</html>";
//        });

        post("/hello", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("name", req.queryParams("name"));
            model.put("age", req.queryParams("age"));

            return render(model, "result.html");
        });
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}