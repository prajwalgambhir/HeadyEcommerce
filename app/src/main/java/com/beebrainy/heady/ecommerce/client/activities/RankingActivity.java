package com.beebrainy.heady.ecommerce.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.client.activities.di.DaggerRAComponent;
import com.beebrainy.heady.ecommerce.client.activities.di.RAComponent;
import com.beebrainy.heady.ecommerce.server.components.ranking.IRanking;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import javax.inject.Inject;

import io.realm.RealmList;

public class RankingActivity extends BaseActivity {

    private LinearLayout ll;
    public static final String KEY_RANKING_ID = "KEY_RANKING_ID";

    private RAComponent raComponent;
    @Inject
    IRanking ranking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        initView();
        raComponent = DaggerRAComponent.builder().build();
        raComponent.inject(this);
        getRanks();
    }

    private void initView() {
        ll = findViewById(R.id.ll);
    }

    private void getRanks() {

        RealmList<RankingEntity> rankings = ranking.getRankings();
        for (final RankingEntity re : rankings) {
            Button btn = new Button(this);
            btn.setText(re.getTitle());
            btn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                    .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RankingActivity.this, ProductListActivity.class);
                    intent.putExtra(KEY_RANKING_ID, re.getId());
                    startActivity(intent);
                }
            });
            ll.addView(btn);
        }
    }
}
