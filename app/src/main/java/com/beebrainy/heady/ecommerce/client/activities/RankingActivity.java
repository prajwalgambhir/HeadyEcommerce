package com.beebrainy.heady.ecommerce.client.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.beebrainy.heady.ecommerce.R;
import com.beebrainy.heady.ecommerce.server.components.ranking.IRanking;
import com.beebrainy.heady.ecommerce.server.components.ranking.RankingBO;
import com.beebrainy.heady.ecommerce.server.models.RankingEntity;

import io.realm.RealmList;

public class RankingActivity extends AppCompatActivity {

    private LinearLayout ll;
    public static final String KEY_RANKING_ID = "KEY_RANKING_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        initView();
        getRanks();

    }

    private void initView() {
        ll = findViewById(R.id.ll);
    }

    private void getRanks() {
        IRanking ranking = new RankingBO();
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
