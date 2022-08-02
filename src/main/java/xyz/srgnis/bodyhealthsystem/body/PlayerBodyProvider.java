package xyz.srgnis.bodyhealthsystem.body;

import xyz.srgnis.bodyhealthsystem.body.impl.PlayerBody;

public interface PlayerBodyProvider {
    public PlayerBody body = null;
    public PlayerBody getBody();
    public void setBody(PlayerBody pb);
}
