using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;

namespace RunnerStudios
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class GameEngine : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        MouseState prevState;
        MouseState currState;
        Level level;
        Platform currPlat;

        public static ContentManager content;

        public GameEngine()
        {
            graphics = new GraphicsDeviceManager(this);
            content = new ContentManager(Services);
            content.RootDirectory = "Content\\";
            level = new Level();
            prevState = Mouse.GetState();
            currState = Mouse.GetState();
            currPlat = null;
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here
            IsMouseVisible = true;

            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);

            // TODO: use this.Content to load your game content here
            Level.LoadContent(content);
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                this.Exit();

            // TODO: Add your update logic here
            currState = Mouse.GetState();

            //Just started dragging
            if (prevState.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Released && 
                currState.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Pressed)
            {
                List<Platform> platforms = level.getPlatforms();
                foreach (Platform p in platforms)
                {
                    if (p.onPlat(currState))
                    {
                        currPlat = p;
                    }
                }
            }

            if (currState.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Pressed &&
                prevState.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Pressed && currPlat != null)
            {
                int moveDist = currState.X - prevState.X;
                currPlat.movePlat(moveDist);
            }

            if (currState.LeftButton == Microsoft.Xna.Framework.Input.ButtonState.Released)
            {
                currPlat = null;
            }
           

            prevState = currState;
            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.White);

            // TODO: Add your drawing code here
            spriteBatch.Begin();

            level.Draw(spriteBatch, gameTime);

            spriteBatch.End();
            base.Draw(gameTime);
        }
    }
}
