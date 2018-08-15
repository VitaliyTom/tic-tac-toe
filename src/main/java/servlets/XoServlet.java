package servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String xoPost(@ModelAttribute("fkey") Game h, Model model) {

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

        winner(mapGame,model);

        runBot(mapGame);

        winner(mapGame,model);

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
            if (mapGame.get(j)==null|| mapGame.get(j)=="") {
                mapGame.put(j, "o");
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
    private String winner( Map mapGame, Model model) {

        //проверка горизонтали
        for (int i = 0; i < 9; i += 3) {
            if (String.valueOf(mapGame.get(i)).equals(mapGame.get(9)) && String.valueOf(mapGame.get(i + 1)).equals(mapGame.get(9)) && String.valueOf(mapGame.get(i + 2)).equals(mapGame.get(9))) {


                model.addAttribute("winner", mapGame.get(9));

                return "pages/winner";
            }
        }
        //проверка вертикали
        for (int i = 0; i < 9; i++) {
            if (mapGame.get(i).equals(mapGame.get(9)) && mapGame.get(i + 3).equals(mapGame.get(9)) && mapGame.get(i + 6).equals(mapGame.get(9))) {
                return "pages/winner";
            }
        }
        //проверка вертикали 2-4-6
        if (mapGame.get(2).equals(mapGame.get(9)) && mapGame.get(4).equals(mapGame.get(9)) && mapGame.get(6).equals(mapGame.get(9))) {

            return "pages/winner";
        }
        //проверка вертикали 0-4-8
        if (mapGame.get(0).equals(mapGame.get(9)) && mapGame.get(4).equals(mapGame.get(9)) && mapGame.get(8).equals(mapGame.get(9))) {

            return "pages/winner";
        }
        //если нет выйгрыша
        for (int i = 0; i < 9; i++) {
            if (mapGame.get(i) == null || mapGame.get(i) == "") {
                return null;
            }
        }
        //ничья
        return "pages/winner";
    }
}
