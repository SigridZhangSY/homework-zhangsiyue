package rich;

public class Tool {
    private ToolType type;
    private int pointPrice;

    public Tool(ToolType type) {
        this.type = type;
        pointPrice = type.getPrice();
    }

    public ToolType getType() {
        return type;
    }

    public int getPointPrice() {
        return pointPrice;
    }

    public enum ToolType{
        BLOCK {
            @Override
            public int getPrice() {
                return 50;
            }
        },
        ROBOT {
            @Override
            public int getPrice() {
                return 50;
            }
        },
        BOMB {
            @Override
            public int getPrice() {
                return 30;
            }
        };

        public abstract int getPrice();
    }
}
