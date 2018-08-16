package servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServlet;
import java.util.*;


@Controller
@RequestMapping("/controller")

public class XoServlet extends HttpServlet {

 //   private List<Integer> key = new ArrayList<>();







    //ПОСТ
    @RequestMapping(value = "/run", method = RequestMethod.POST)
    public String xoPost(@ModelAttribute("fkey") Game h,Model model) {
        String winn;
        Map<String, String> mapGame = new HashMap();

        mapGame.put("0", h.getK1());
        mapGame.put("1", h.getK2());
        mapGame.put("2", h.getK3());
        mapGame.put("3", h.getK4());
        mapGame.put("4", h.getK5());
        mapGame.put("5", h.getK6());
        mapGame.put("6", h.getK7());
        mapGame.put("7", h.getK8());
        mapGame.put("8", h.getK9());
        mapGame.put("9","x");
        winn = "победил пользователь";

        if(winner(mapGame,model, winn)==true){
            return "pages/winner";
        }

        runBot(mapGame);

        winn = "победил бот";

        if(winner(mapGame,model, winn)==true){
            return "pages/winner";
        }

        h.setK1(String.valueOf(mapGame.get("0")));
        h.setK2(String.valueOf(mapGame.get("1")));
        h.setK3(String.valueOf(mapGame.get("2")));
        h.setK4(String.valueOf(mapGame.get("3")));
        h.setK5(String.valueOf(mapGame.get("4")));
        h.setK6(String.valueOf(mapGame.get("5")));
        h.setK7(String.valueOf(mapGame.get("6")));
        h.setK8(String.valueOf(mapGame.get("7")));
        h.setK9(String.valueOf(mapGame.get("8")));

      model.addAttribute("fKey", h);
        return "pages/game";
    }






    //Ход бота
    private void runBot(Map mapGame) {
        mapGame.put("9","o");
        Random rnd = new Random();

        for (int i = 0; i < 1; i--) {
            int j = rnd.nextInt(8);
            if (mapGame.get(String.valueOf(j)).equals(null)|| mapGame.get(String.valueOf(j)).equals("")) {
                mapGame.put(String.valueOf(j), "o");
                return;
            }
       }
    }

    //ГЕТ
    @RequestMapping(value = "/run", method = RequestMethod.GET)
    public String xoGet(Model model) {
        Game h = new Game();
        model.addAttribute("fKey", h);
        return "pages/game";
    }

    //ПРОВЕРКА НА ПОБЕДИТЕЛЯ
    private Boolean winner( Map mapGame, Model model, String winn) {
        //проверка горизонтали
//        Boolean boo = false;
        for (int i = 0; i < 9; i += 3) {
            if (mapGame.get(String.valueOf(i)).equals(mapGame.get("9")) && mapGame.get(String.valueOf(i + 1)).equals(mapGame.get("9")) &&
                    mapGame.get(String.valueOf(i + 2)).equals(mapGame.get("9"))) {
                model.addAttribute("winner", winn);

                return true;
            }
        }
        //проверка вертикали
        for (int i = 0; i < 3; i++) {
            if (mapGame.get(String.valueOf(i)).equals(mapGame.get("9")) &&mapGame.get(String.valueOf(i + 3)).equals(mapGame.get("9")) &&
                    mapGame.get(String.valueOf(i + 6)).equals(mapGame.get("9"))) {
                model.addAttribute("winner", winn);
                return true;
            }
        }
        //проверка диагонали 2-4-6
        if (mapGame.get("2").equals(mapGame.get("9")) &&mapGame.get("4").equals(mapGame.get("9")) &&
                mapGame.get("6").equals(mapGame.get("9"))) {
            model.addAttribute("winner", winn);
            return true;
        }
        //проверка диагонали 0-4-8
        if (mapGame.get("0").equals(mapGame.get("9")) &&mapGame.get("4").equals(mapGame.get("9")) &&
                mapGame.get("8").equals(mapGame.get("9"))) {
            model.addAttribute("winner", winn);
            return true;
        }
        //если нет выйгрыша
        for (int i = 0; i < 9; i++) {
            if (mapGame.get(String.valueOf(i)).equals(null) || mapGame.get(String.valueOf(i)).equals("")) {
                return false;
            }
        }
        //ничья
        winn="Ничья";
        model.addAttribute("winner", winn);
        return true;
    }
}
