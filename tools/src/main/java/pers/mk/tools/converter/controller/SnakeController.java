package pers.mk.tools.converter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mk.tools.converter.model.SnakeNode;

/**
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.controller
 * @Date 2023/3/8 11:00
 * @Version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/snake")
public class SnakeController {

    private static SnakeNode snake = new SnakeNode(50, 50, null);

    @RequestMapping("/index")
    public String index(){
        return "/snake/index";
    }

    @RequestMapping("/direction")
    @ResponseBody
    public SnakeNode direction(Integer x,Integer y,Boolean isEatFood){
        if (isEatFood){
            snake = new SnakeNode(x,y,snake);
        }else {
            snake = this.snakeAdd(x,y,snake);
        }
        return snake;
    }

    @RequestMapping("/reset")
    @ResponseBody
    public String reset(){
        snake = new SnakeNode(50, 50, null);
        return "重置成功~";
    }

    private SnakeNode snakeAdd(Integer x,Integer y,SnakeNode tp){
        if (tp.getNext() != null){
            snakeAdd(tp.getX(),tp.getY(),tp.getNext());
            tp.setX(x);
            tp.setY(y);
            return tp;
        }else {
            tp.setX(x);
            tp.setY(y);
            return tp;
        }
    }

}
