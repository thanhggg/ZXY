package com.example.hiep.test1.adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hiep.test1.R;
import com.example.hiep.test1.db.CategoryObject;
import com.example.hiep.test1.db.SMSObject;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;

import java.util.ArrayList;

public class PopularSMSAdapter extends BaseAdapter {
    private int resouce;
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Object> arr;
    private OnItemClickListener mListener;

    private final int TYPE_SMS = 0;
    private final int TYPE_ADS = 1;

    public PopularSMSAdapter(Context context, int resouce, ArrayList<Object> list) {
        this.context = context;
        this.resouce = resouce;
        inflater = LayoutInflater.from(context);
        this.arr = list;
    }

    public interface OnItemClickListener {

        void onItemClicked(int pos, String msg);
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public CategoryObject getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (type == TYPE_SMS) {
            final SMSObject sms = (SMSObject) arr.get(position);
            ViewHolder mHolder = new ViewHolder();
            if (convertView == null) {
                convertView = inflater.inflate(resouce, null);
                mHolder.tvSMS = convertView.findViewById(R.id.tvSMSName);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }

            if (sms != null) {
                mHolder.tvSMS.setTextColor(context.getResources().getColor(R.color.red));
                mHolder.tvSMS.setText(sms.getContent());

                mHolder.tvSMS.getRootView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            mListener.onItemClicked(position, sms.getContent());
                        }
                    }
                });
            }
        } else {
            UnifiedNativeAd item = (UnifiedNativeAd) arr.get(position);
            AdViewHolder adViewHolder = new AdViewHolder();
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_ad_view, null);

                adViewHolder.adview = convertView.findViewById(R.id.ly_ad_view);

                adViewHolder.ivAdIcon = convertView.findViewById(R.id.iv_ad_icon);
                adViewHolder.ivAdImage = convertView.findViewById(R.id.iv_ad_image);
                adViewHolder.tvAdHeadline = convertView.findViewById(R.id.tv_ad_headline);
                adViewHolder.mvAdMedia = convertView.findViewById(R.id.mv_ad_media_view);

                convertView.setTag(adViewHolder);
            } else {
                adViewHolder = (AdViewHolder) convertView.getTag();
            }

            if (item.getIcon() != null) {
                adViewHolder.ivAdIcon.setImageDrawable(item.getIcon().getDrawable());
            }
            adViewHolder.adview.setIconView(adViewHolder.ivAdIcon);
            adViewHolder.tvAdHeadline.setText(item.getHeadline());
            adViewHolder.adview.setHeadlineView(adViewHolder.tvAdHeadline);

            if (item.getVideoController().hasVideoContent()) {
                adViewHolder.ivAdImage.setVisibility(View.GONE);

                final VideoController videoController = item.getVideoController();
                adViewHolder.adview.setMediaView(adViewHolder.mvAdMedia);

                videoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                    @Override
                    public void onVideoEnd() {
                        super.onVideoEnd();
                    }
                });
            } else {
                adViewHolder.mvAdMedia.setVisibility(View.GONE);
                if (item.getImages().size() > 0) {
                    adViewHolder.ivAdImage.setImageDrawable(item.getImages().get(0).getDrawable());
                    adViewHolder.adview.setImageView(adViewHolder.ivAdImage);
                }
            }

            adViewHolder.adview.setNativeAd(item);
        }

        return convertView;
    }

    private class ViewHolder {
        TextView tvSMS;
    }

    private class AdViewHolder {

        UnifiedNativeAdView adview;
        ImageView ivAdIcon;
        ImageView ivAdImage;
        TextView tvAdHeadline;
        MediaView mvAdMedia;

    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        return arr.get(position) instanceof SMSObject ? TYPE_SMS : TYPE_ADS;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
