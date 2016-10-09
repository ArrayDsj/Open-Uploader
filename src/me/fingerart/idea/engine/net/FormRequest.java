package me.fingerart.idea.engine.net;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by FingerArt on 16/10/7.
 */
public class FormRequest extends BaseRequest {
    private LinkedHashMap<String, File> mParamFile;

    public FormRequest(FormRequestBuilder builder) {
        mUrl = builder.mUrl;
        mParamStr = builder.mParamStr;
        mParamFile = builder.mParamFile;
    }

    @Override
    protected HttpRequestBase getRelRequest() {
        HttpPost post = new HttpPost(mUrl);
        ArtHttp.addTag(post.toString(), post);
        ProgressMultipartEntity entity = new ProgressMultipartEntity(mCallback);
        entity.addPart(mParamStr);
        if (mParamFile != null)
            for (Map.Entry<String, File> entry : mParamFile.entrySet()) {
                entity.addPart(entry.getKey(), entry.getValue());
            }
        post.setEntity(entity);
        return post;
    }
}
