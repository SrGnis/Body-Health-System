package xyz.srgnis.bodyhealthsystem.body;

import xyz.srgnis.bodyhealthsystem.body.player.PlayerBody;

public interface PlayerBodyProvider {
    public PlayerBody body = null;
    public PlayerBody getBody();
    public void setBody(PlayerBody pb);
}
