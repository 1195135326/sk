package com.ts.system.postManager.control;

import com.ts.entity.ResultInfo;
import com.ts.system.postManager.UI.PostInfo;
import com.ts.system.postManager.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Positive;

@Controller
@RequestMapping("/post")
public class PostController
{
    @Autowired
    private PostService postService;

    @ResponseBody
    @RequestMapping("/queryPost")
    public PostInfo queryPost(@RequestBody PostInfo postInfo){
        System.out.print(postInfo.getName());
//        ResultInfo rs = new ResultInfo();
//        rs =  postService.searchAllPost("","",1,10);
        return postInfo;
    }

//    @RequestMapping(value="person", method = RequestMethod.POST)
//    public @ResponseBody Person post( @RequestBody final Person person) {
//
//        System.out.println(person.getId() + " " + person.getName());
//        return person;
//    }




}
