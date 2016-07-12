package pku.yang.tool;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * Created by LinkedME07 on 16/7/9.
 */
@Component
public class PropertiesUtils implements EmbeddedValueResolverAware {

    private StringValueResolver stringValueResolver;

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        stringValueResolver = resolver;
    }

    public String getPropertiesValue(String name){
        String dbTableType;
        try{
            dbTableType =  stringValueResolver.resolveStringValue(name);
        }catch (Exception e){
            return null;
        }
        return dbTableType;
    }
}
