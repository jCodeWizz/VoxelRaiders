package dev.codewizz.gfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import dev.codewizz.utils.Logger;

public class Particles {

    private final AssetManager assets;
    private final ParticleEffectLoader.ParticleEffectLoadParameter loadParam;


    private final ParticleSystem particleSystem;
    private final Array<ParticleEffect> activeEffects = new Array<>();

    public Particles(Camera camera) {
        assets = new AssetManager();

        BillboardParticleBatch billboardBatch = new BillboardParticleBatch();
        billboardBatch.setCamera(camera.getPerspectiveCamera());

        particleSystem = new ParticleSystem();
        particleSystem.add(billboardBatch);

        loadParam = new ParticleEffectLoader.ParticleEffectLoadParameter(particleSystem.getBatches());
    }

    public void load() {
        assets.load("particles/dust.pfx", ParticleEffect.class, loadParam);
        assets.finishLoading();
    }

    public void spawn(String effectPath, Vector3 pos) {

        ParticleEffect template = assets.get(effectPath, ParticleEffect.class);
        ParticleEffect instance = template.copy();

        instance.init();
        instance.start();
        instance.translate(pos);
        float scale = 0.33f;
        instance.scale(scale, scale, scale);

        particleSystem.add(instance);
        activeEffects.add(instance);
    }

    public void update(float delta) {
        for (int i = activeEffects.size - 1; i >= 0; i--) {
            ParticleEffect effect = activeEffects.get(i);
            effect.update(delta);
            if (effect.isComplete()) {
                activeEffects.removeIndex(i);
            }
        }
    }

    public void render(ModelBatch batch) {
        update(Gdx.graphics.getDeltaTime());
        particleSystem.begin();
        particleSystem.draw();
        particleSystem.end();
        batch.render(particleSystem);
    }
}
