package greedy;

public class JumpGame {

    //Tumhe ek array nums diya hai.
    //Har index pe nums[i] batata hai maximum kitna jump le sakte ho.
    //Start index = 0
    //Goal = last index tak pahunchna possible hai ya nahi?

    //? Last index se peeche ki taraf sochte hain

    //targetIndex = wo position jahan tak pahunchna zaroori hai
    //
    //Initially, target = last index
    //
    //Peeche se check karo:
    //
    //Agar current index i se targetIndex reachable hai
    //(i + nums[i] >= targetIndex)
    //
    //Toh target ko shift karke i bana do
    //
    //End me agar targetIndex == 0, matlab start se end tak pahunch sakte ho

    //“Last index ko catch karte hue peeche aao; agar 0 pakad liya, game jeet gaye.”
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int targetIndex = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (targetIndex <= (i + nums[i])) {
                targetIndex = i;
            }
        }
        return targetIndex == 0;
    }
}
