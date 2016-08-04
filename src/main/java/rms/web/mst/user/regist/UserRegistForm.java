package rms.web.mst.user.regist;

import java.util.List;

import rms.web.com.utils.SelectOptionEntity;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * ユーザ登録画面フォーム
 * @author
 */
public class UserRegistForm extends rms.web.com.abstracts.AbstractForm {

    /* 入力チェック宣言 ----------------------------------------------------- */
    //@formatter:off
    /** 入力チェック：新規 */
    protected static interface Insert{};
    /** 入力チェック：更新 */
    protected static interface Update{};
    //@formatter:on

    /* 定数宣言 ------------------------------------------------------------- */
    /** 画面表示モード：新規 */
    public static final String VIEW_MODE_INSERT = "insert";
    /** 画面表示モード：更新 */
    public static final String VIEW_MODE_UPDATE = "update";

    /* 排他制御用 ----------------------------------------------------------- */
    /** バージョン */
    private int version;

    /* 変数宣言 ------------------------------------------------------------- */
    /** 画面表示モード */
    private String viewMode;

    /** ユーザID */
    @NotEmpty(message = "ユーザIDは{NotEmpty.message}", groups = { Insert.class, Update.class })
    @Size(max = 10, message = "ユーザIDは{Size.message}", groups = { Insert.class, Update.class })
    private String userId;
    /** パスワード */
    @NotEmpty(message = "パスワードは{NotEmpty.message}", groups = { Insert.class })
    @Size(max = 10, message = "パスワードは{Size.message}", groups = { Insert.class })
    private String password;
    /** ユーザ名 */
    @NotEmpty(message = "ユーザ名は{NotEmpty.message}", groups = { Insert.class, Update.class })
    @Size(max = 10, message = "ユーザ名は{Size.message}", groups = { Insert.class, Update.class })
    private String userNm;
    /** メールアドレス */
    @Email(message = "メールアドレスの{Email.message}", groups = { Insert.class, Update.class })
    private String email;

    /** 承認者１ID */
    private String approveUserId1;
    /** 承認者２ID */
    private String approveUserId2;
    /** 承認者３ID */
    private String approveUserId3;

    /** 役割：申請者フラグ */
    private String roleApplyFlg;
    /** 役割：承認者フラグ */
    private String roleApproveFlg;
    /** 役割：管理者フラグ */
    private String roleAdminFlg;

    /** 承認者リスト(selectbox用) */
    private List<SelectOptionEntity> approveList;

    /* getter/setter -------------------------------------------------------- */
    /**
     * バージョンを取得します。
     * @return バージョン
     */
    public int getVersion() {
        return version;
    }

    /**
     * バージョンを設定します。
     * @param version バージョン
     */
    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * 画面表示モードを取得します。
     * @return 画面表示モード
     */
    public String getViewMode() {
        return viewMode;
    }

    /**
     * 画面表示モードを設定します。
     * @param viewMode 画面表示モード
     */
    public void setViewMode(String viewMode) {
        this.viewMode = viewMode;
    }

    /**
     * ユーザIDを取得します。
     * @return ユーザID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定します。
     * @param userId ユーザID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * パスワードを取得します。
     * @return パスワード
     */
    public String getPassword() {
        return password;
    }

    /**
     * パスワードを設定します。
     * @param password パスワード
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * ユーザ名を取得します。
     * @return ユーザ名
     */
    public String getUserNm() {
        return userNm;
    }

    /**
     * ユーザ名を設定します。
     * @param userNm ユーザ名
     */
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    /**
     * メールアドレスを取得します。
     * @return メールアドレス
     */
    public String getEmail() {
        return email;
    }

    /**
     * メールアドレスを設定します。
     * @param email メールアドレス
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 承認者１IDを取得します。
     * @return 承認者１ID
     */
    public String getApproveUserId1() {
        return approveUserId1;
    }

    /**
     * 承認者１IDを設定します。
     * @param approveUserId1 承認者１ID
     */
    public void setApproveUserId1(String approveUserId1) {
        this.approveUserId1 = approveUserId1;
    }

    /**
     * 承認者２IDを取得します。
     * @return 承認者２ID
     */
    public String getApproveUserId2() {
        return approveUserId2;
    }

    /**
     * 承認者２IDを設定します。
     * @param approveUserId2 承認者２ID
     */
    public void setApproveUserId2(String approveUserId2) {
        this.approveUserId2 = approveUserId2;
    }

    /**
     * 承認者３IDを取得します。
     * @return 承認者３ID
     */
    public String getApproveUserId3() {
        return approveUserId3;
    }

    /**
     * 承認者３IDを設定します。
     * @param approveUserId3 承認者３ID
     */
    public void setApproveUserId3(String approveUserId3) {
        this.approveUserId3 = approveUserId3;
    }

    /**
     * 役割：申請者フラグを取得します。
     * @return 役割：申請者フラグ
     */
    public String getRoleApplyFlg() {
        return roleApplyFlg;
    }

    /**
     * 役割：申請者フラグを設定します。
     * @param roleApplyFlg 役割：申請者フラグ
     */
    public void setRoleApplyFlg(String roleApplyFlg) {
        this.roleApplyFlg = roleApplyFlg;
    }

    /**
     * 役割：承認者フラグを取得します。
     * @return 役割：承認者フラグ
     */
    public String getRoleApproveFlg() {
        return roleApproveFlg;
    }

    /**
     * 役割：承認者フラグを設定します。
     * @param roleApproveFlg 役割：承認者フラグ
     */
    public void setRoleApproveFlg(String roleApproveFlg) {
        this.roleApproveFlg = roleApproveFlg;
    }

    /**
     * 役割：管理者フラグを取得します。
     * @return 役割：管理者フラグ
     */
    public String getRoleAdminFlg() {
        return roleAdminFlg;
    }

    /**
     * 役割：管理者フラグを設定します。
     * @param roleAdminFlg 役割：管理者フラグ
     */
    public void setRoleAdminFlg(String roleAdminFlg) {
        this.roleAdminFlg = roleAdminFlg;
    }

    /**
     * 承認者リスト(selectbox用)を取得します。
     * @return 承認者リスト(selectbox用)
     */
    public List<SelectOptionEntity> getApproveList() {
        return approveList;
    }

    /**
     * 承認者リスト(selectbox用)を設定します。
     * @param approveList 承認者リスト(selectbox用)
     */
    public void setApproveList(List<SelectOptionEntity> approveList) {
        this.approveList = approveList;
    }

}
