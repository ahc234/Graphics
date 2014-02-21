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
    public class Platform
    {
        private int platPos;        //Offset from pathStart; the distance in x between the LHS end of the path and the LHS end of the platform
        private int platLen;
        private Point pathStart;
        private int pathLen;
        private const int platDepth = 10;   //Height of platform; y position of platform +/- platDepth

        static Texture2D platform;
        static Texture2D path;

        public Platform(int platPos, int platLen, Point pathStart, int pathLen)
        {
            this.platPos = platPos;
            this.platLen = platLen;
            this.pathStart = pathStart;
            this.pathLen = pathLen;
        }

        public static void LoadContent(ContentManager content)
        {
            platform = content.Load<Texture2D>("Images\\platform");
            path = content.Load<Texture2D>("Images\\path");
        }

        public void Draw(SpriteBatch spriteBatch, GameTime gameTime)
        {
            spriteBatch.Draw(path, new Vector2(pathStart.X - 25, pathStart.Y - platDepth), null, Color.White, 0.0f, Vector2.Zero,
                   1.0f, SpriteEffects.None, 1.0f);
            spriteBatch.Draw(platform, new Vector2(pathStart.X + platPos, pathStart.Y - platDepth), null, Color.White, 0.0f, Vector2.Zero,
                               1.0f, SpriteEffects.None, 1.0f);
        }

        public bool onPlat(MouseState state)
        {
            return (state.Y > pathStart.Y - platDepth && state.Y < pathStart.Y + platDepth
                && state.X > pathStart.X + platPos && state.X < pathStart.X + platPos + platLen);
        }

        public void movePlat(int distance)
        {
            int extraSpace = 0;
            int moveDist = 0;

            if (distance >= 0)
            {
                extraSpace = (pathStart.X + pathLen) - (pathStart.X + platPos + platLen);
                moveDist = Math.Min(extraSpace, distance);
            }
            else
            {
                extraSpace = platPos;
                moveDist = Math.Max(-extraSpace, distance);
            }
            platPos += moveDist;
        }

        public bool isOnPlat(Vector2 pos)
        {
            //return true if character is within the bounds of the platform
            return (pos.X > pathStart.X + platPos && pos.X < pathStart.X + platPos + platLen &&
                pos.Y > pathStart.Y - platDepth && pos.Y < pathStart.Y + platDepth);
        }
    }
}
