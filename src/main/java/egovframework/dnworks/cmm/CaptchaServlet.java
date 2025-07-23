package egovframework.dnworks.cmm;

import java.awt.Color;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.FlatColorBackgroundProducer;
import nl.captcha.servlet.CaptchaServletUtil;
import nl.captcha.text.producer.NumbersAnswerProducer;

public class CaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Captcha captcha = new Captcha.Builder(180, 44)
                .addText(new NumbersAnswerProducer(6)) // default: 6개의 숫자+문자
                .addNoise().addNoise()
                .addBackground(new FlatColorBackgroundProducer(Color.WHITE)) // 기본 하얀색 배경
                .build();
 
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Max-Age", 0);
        response.setContentType("image/png");
        CaptchaServletUtil.writeImage(response, captcha.getImage()); // 이미지 그리기
        request.getSession().setAttribute("captcha", captcha.getAnswer()); // 값 저장
    }

}
