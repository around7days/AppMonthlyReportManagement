<#-- このテンプレートに対応するデータモデルのクラスは org.seasar.doma.extension.gen.EntityListenerDesc です -->
<#import "/lib.ftl" as lib>
<#if lib.copyright??>
${lib.copyright}
</#if>
<#if packageName??>
package ${packageName};
</#if>

<#list importNames as importName>
import ${importName};
</#list>
import rms.web.base.UserInfo;
import rms.web.com.utils.AuthenticationUtils;
import java.time.LocalDateTime;

/**
 * ${simpleName}クラス
<#if lib.author??>
 * @author ${lib.author}
</#if>
 */
public class ${simpleName}<#if superclassSimpleName??> extends ${superclassSimpleName}<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}><#else> implements EntityListener<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}></#if> {
<#if !superclassSimpleName??>

    @Override
    public void preInsert(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PreInsertContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
        //@formatter:off
        UserInfo userInfo = AuthenticationUtils.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getVersion() == null) entity.setVersion(0);
        if (entity.getDelFlg() == null)  entity.setDelFlg(0);
        if (entity.getInsId() == null)   entity.setInsId(userInfo.getUserId());
        if (entity.getInsDate() == null) entity.setInsDate(now);
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preUpdate(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PreUpdateContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
        //@formatter:off
        UserInfo userInfo = AuthenticationUtils.getPrincipal();
        LocalDateTime now = LocalDateTime.now();
        if (entity.getUpdId() == null)   entity.setUpdId(userInfo.getUserId());
        if (entity.getUpdDate() == null) entity.setUpdDate(now);
        //@formatter:on
    }

    @Override
    public void preDelete(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PreDeleteContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }

    @Override
    public void postInsert(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PostInsertContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }

    @Override
    public void postUpdate(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PostUpdateContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }

    @Override
    public void postDelete(<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName} entity, PostDeleteContext<<#if entityDesc.entityPrefix??>${entityDesc.entityPrefix}</#if>${entityClassSimpleName}> context) {
    }
</#if>
}