package com.byzilio;

import com.badlogic.gdx.graphics.Texture;
import com.byzilio.engine.Engine;
import com.byzilio.engine.Entity;
import com.byzilio.engine.Scene;
import com.byzilio.game.components.LogTextComponent;
import com.byzilio.game.components.Position;
import com.byzilio.game.components.Rigidbody;
import com.byzilio.game.components.Sprite;
import com.byzilio.game.core.ArrayListEngine;
import com.byzilio.game.core.ArrayListEntity;
import com.byzilio.game.core.ArrayScene;
import com.byzilio.game.core.HashMapInput;
import com.byzilio.game.scripts.TestScript;
import com.byzilio.game.systems.InputSystem;
import com.byzilio.game.systems.LogTextSystem;
import com.byzilio.game.systems.MoveSystem;
import com.byzilio.game.systems.RenderSystem;
import com.byzilio.game.systems.ScriptSystem;

public class Game extends com.badlogic.gdx.Game {//Ну не умею я называть правильно классы

	/*
		Внимание! Это все пробный вариант.
		Я ECS пишу 3 раз в жизни.
		Так что код не был идеально продуман заранее.
		Поэтому тут очень много тупых мест появившихся из-за починки косяков.
		Например, функция create в Component, GameObject их не удобно использовать.
	 */

	Engine engine;

	@Override
	public void create () {
		engine = new ArrayListEngine(new HashMapInput());
		engine.add(new InputSystem());
        engine.add(new MoveSystem());
        engine.add(new ScriptSystem());
		engine.add(new LogTextSystem());
		engine.add(new RenderSystem());

		Scene scene = new ArrayScene();

		Entity e = new ArrayListEntity();
		/*
		scene.add(e);
		e.add(new LogTextComponent("aAAAAAA"));
		e.add(new LogTextComponent("134134124"));
		e.add(new LogTextComponent());
		e.add(new LogTextComponent());
		e.add(new LogTextComponent("adfghfadshafs"));

		e = new ArrayListEntity();
		scene.add(e);
		e.add(new Position(10, 10));
		e.add(new Sprite(new Texture("badlogic.jpg"), 100, 120));

		e = new ArrayListEntity();
		scene.add(e);
		e.add(new Position(110, 10));
		e.add(new Sprite(new Texture("badlogic.jpg"), 50, 220));

		e = new ArrayListEntity();
		*/
		engine.changeScene(scene);


		e.add(new Position(0, 30));
		e.add(new TestScript());
		e.add(new Sprite(new Texture("badlogic.jpg"), 100, 150));
        e.add(new Rigidbody(2,1,1.5f,1,2,2,0,0,0));
		scene.add(e);
		setScreen(engine);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		engine.dispose();
		//dispose в libGDX обязателен его же обьекты нужно таким образом удалять.
		//Иначе на андроиде будет мусор вместо текстур например.
	}
}
