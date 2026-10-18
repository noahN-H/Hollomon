public class Card implements Comparable<Card>{
    final int id;
    final String name;
    final Rank rank;
    public Long salePrice;

    //enums used as it is the easiest way to provide an order to the Rank attribute of the Card
    public enum Rank {
        UNIQUE, RARE, UNCOMMON, COMMON
    }

    public Card(int id, String name, Rank rank, Long salePrice) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.salePrice = salePrice;
    }

    @Override
    public int compareTo(Card otherCard) {
        int compareRank = this.rank.compareTo(otherCard.rank);
        int compareName = this.name.compareTo(otherCard.name);
        int compareId =  Long.compare(this.id, otherCard.id);

        if (compareRank != 0) return compareRank;
        if (compareName != 0) return compareName;
        return compareId;
    }

    @Override
    public String toString(){
        return (this.id + ": " + this.name + " " + this.rank + " " + this.salePrice + "\n");
    }
}