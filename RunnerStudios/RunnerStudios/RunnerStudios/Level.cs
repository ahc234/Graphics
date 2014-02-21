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
    public class Level
    {
        private List<Platform> platforms;

        public Level()
        {
            platforms = new List<Platform>();
            platforms.Add(new Platform(100, 100, new Point(200, 200), 400));
            platforms.Add(new Platform(250, 100, new Point(300, 300), 400));
        }

        public static void LoadContent(ContentManager content)
        {
            Platform.LoadContent(content);

        }

        public void Draw(SpriteBatch spriteBatch, GameTime gameTime)
        {
            foreach (Platform p in platforms)
            {
                p.Draw(spriteBatch, gameTime);
            }
        }

        public List<Platform> getPlatforms()
        {
            return platforms;
        }
    }
}
