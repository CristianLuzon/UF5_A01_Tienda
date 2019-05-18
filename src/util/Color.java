package util;

public enum Color
{
    CORRECTO("\u001B[32m"),
    ERROR("\u001B[31m"),
    SERIE("\u001B[0m");
    
    private String color;
    
    private Color(String color)
    {
        this.color = color;
    }
    
    @Override
    public String toString()
    {
        return color;
    }
    
}
