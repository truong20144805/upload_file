package Controller;

import Model.Music;
import Model.MusicForm;
import Service.MusicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;


@Controller
public class MusicController {
    @Value("${file-upload}")
    private String fileUpload;

    private final MusicService service = new MusicService();


    @GetMapping("/create")
    public ModelAndView showCreateForm(@ModelAttribute("musicForm") MusicForm form){
        ModelAndView modelAndView = new ModelAndView("/create");
        return modelAndView;
    }
    @PostMapping("/saveFile")
    public ModelAndView saveProduct(@ModelAttribute("musicForm") MusicForm form){
        MultipartFile multipartFile = form.getLink();
        String fileName = multipartFile.getOriginalFilename();
        try{
            FileCopyUtils.copy(form.getLink().getBytes(),new File(fileUpload,fileName));
        } catch (Exception e){
            e.printStackTrace();
        }
        Music music = new Music(form.getId(),form.getName(),form.getSinger(),form.getType(),fileName);
        service.save(music);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("message", "Create success");
        return modelAndView;
    }
}
