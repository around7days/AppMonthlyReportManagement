package mms.uniq.menu;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mms.com.consts.PageIdConst;
import mms.com.security.UserInfo;

/**
 * メニュー登録画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
public class MenuController extends mms.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    /** デフォルトマッピングURL */
    public static final String MAPPING_URL = "/menu";

    /**
     * メニュー画面初期表示
     * @param userInfo
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL)
    public String init(@AuthenticationPrincipal UserInfo userInfo,
                       HttpSession session,
                       Model model) {
        logger.debug("ユーザーID：{}", userInfo.getUserId());
        logger.debug("ユーザー名：{}", userInfo.getUserNm());

        // TODO どっかのタイミングできれいにする
        // 個別セッションの破棄
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            logger.info("session key: {}", key);
            if (!"SPRING_SECURITY_CONTEXT".equals(key)
                && !"org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN".equals(key)) {
                session.removeAttribute(key);
            }
        }

        return PageIdConst.Com.MENU;
    }

    /**
     * ユーザー一覧画面に遷移
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "m001")
    public String M001(Model model) {
        return "redirect:/mst/user/search?init";
    }

    /**
     * ユーザー登録画面に遷移
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "m002")
    public String M002(Model model) {
        return "redirect:/mst/user/regist?initNew";
    }

    @RequestMapping(value = MAPPING_URL, params = "t001")
    public String T001(Model model) {
        return "html/月報状況一覧";
    }

    @RequestMapping(value = MAPPING_URL, params = "t002")
    public String T002(Model model) {
        return "html/月報申請";
    }

    @RequestMapping(value = MAPPING_URL, params = "t003")
    public String T003(Model model) {
        return "html/月報承認";
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = MAPPING_URL, params = "e001")
    public String T004(Model model) throws Exception {
        if (true) {
            throw new Exception("意図的な強制Exception");
        }
        return "";
    }

}
