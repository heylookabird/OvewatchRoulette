package roulette.overwatchroulette.navigation;

/**
 * Created by Harjit on 5/14/2016.
 */
public class NavDrawer {
    private String title;
    private int icon;

    public NavDrawer() { }

    public NavDrawer(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public NavDrawer(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public int getIcon() {
        return this.icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
