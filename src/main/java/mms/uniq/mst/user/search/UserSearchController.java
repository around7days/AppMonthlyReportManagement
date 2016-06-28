package mms.uniq.mst.user.search;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.seasar.doma.jdbc.SelectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import mms.com.consts.PageIdConst;
import mms.com.doma.entity.MUser;
import mms.com.page.PageInfo;
import mms.com.utils.SelectOptionsUtil;

/**
 * ユーザ一覧画面コントローラー
 * @author
 */
@Controller
@Transactional(rollbackFor = Exception.class)
@SessionAttributes(value = "userSearchForm")
public class UserSearchController extends mms.com.abstracts.AbstractController {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(UserSearchController.class);

    /** デフォルトマッピングURL */
    public static final String MAPPING_URL = "/mst/user/search";

    /** ユーザ一覧画面フォーム */
    @ModelAttribute(value = "userSearchForm")
    UserSearchForm setupForm() {
        return new UserSearchForm();
    }

    /** ユーザー一覧画面Dao */
    @Autowired
    UserSearchDao dao;

    /**
     * 初期処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "init")
    public String init(UserSearchForm form,
                       Model model) {
        // 初期値設定
        form.setUserNm("ユーザー０");

        return PageIdConst.Mst.USER_SEARCH;
    }

    /**
     * 検索処理
     * @param form
     * @param bindingResult
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "search")
    public String search(@Valid UserSearchForm form,
                         BindingResult bindingResult,
                         Model model) {
        logger.debug("フォーム情報：{}", form.toString());

        // 入力チェック
        if (bindingResult.hasErrors()) {
            logger.debug(bindingResult.getAllErrors().toString());
            return PageIdConst.Mst.USER_SEARCH;
        }

        /*
         * 検索処理
         */
        // ページング設定
        PageInfo pageInfo = form.getPageInfo();
        SelectOptions options = SelectOptionsUtil.get(pageInfo);

        // 検索処理
        List<MUser> result = dao.searchUser(form, options);
        logger.debug("検索結果(全件)：{}件", options.getCount());
        logger.debug("検索結果：{}件", result.size());
        result.forEach(obj -> logger.debug(ToStringBuilder.reflectionToString(obj)));
        if (result.isEmpty()) {
            bindingResult.reject("", "検索結果は存在しません");
            return PageIdConst.Mst.USER_SEARCH;
        }

        // 検索結果格納
        pageInfo.setTotalSize(options.getCount());
        form.setResultList(result);

        return PageIdConst.Mst.USER_SEARCH;
    }

    // TODO Serviceクラスに移行時に検索と再検索ロジックを一緒にしたい
    /**
     * 再検索処理
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "reSearch")
    public String reSearch(UserSearchForm form,
                           Model model) {
        logger.debug("フォーム情報：{}", form.toString());

        /*
         * 検索処理
         */
        // ページング設定
        PageInfo pageInfo = form.getPageInfo();
        SelectOptions options = SelectOptionsUtil.get(pageInfo);

        // 検索処理
        List<MUser> result = dao.searchUser(form, options);
        logger.debug("検索結果(全件)：{}件", options.getCount());
        logger.debug("検索結果：{}件", result.size());
        result.forEach(obj -> logger.debug(ToStringBuilder.reflectionToString(obj)));

        // 検索結果格納
        pageInfo.setTotalSize(options.getCount());
        form.setResultList(result);

        return PageIdConst.Mst.USER_SEARCH;
    }

    /**
     * 前ページング処理<br>
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pagePrev")
    public String pagePrev(UserSearchForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().prev();

        return "redirect:/mst/user/search?reSearch";
    }

    /**
     * 次ページング処理<br>
     * @param form
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "pageNext")
    public String pageNext(UserSearchForm form,
                           Model model) {
        // ページング設定
        form.getPageInfo().next();

        return "redirect:/mst/user/search?reSearch";
    }

    /**
     * ユーザー新規処理
     * @return
     */
    @RequestMapping(value = MAPPING_URL, params = "new")
    public String selectNew() {
        return "redirect:/mst/user/regist/init/new/";
    }

    /**
     * ユーザー選択処理
     * @param form
     * @param index
     * @param model
     * @return
     */
    @RequestMapping(value = MAPPING_URL + "/select/{index}")
    public String select(UserSearchForm form,
                         @PathVariable int index,
                         Model model) {
        logger.debug("選択値：{}", index);
        logger.debug("フォーム情報：{}", form.toString());

        // 選択したユーザー情報
        MUser user = form.getResultList().get(index);
        logger.debug("選択ユーザ情報：{}", user.toString());

        return "redirect:/mst/user/regist/init/update/" + user.getUserId();
    }
}
