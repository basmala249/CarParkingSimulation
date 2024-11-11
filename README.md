🚗 Java Car Park Simulation 🚗
Welcome to Car Park Frenzy — the ultimate parking chaos simulator! 😄

🅿️ About This Project
. This Java project is a simulation of a car park with only 4 parking spots and 3 busy gates using  threads and semaphores. when cars line up at each gate, competing to get one of the few available slots — it's like rush hour, but in your code!
🎮 How It Works
. There are 3 gates (G1, G2, G3), each managed by a thread. Think of them as gatekeepers — but not very patient ones! 🚧
. The car park has just 4 precious slots, managed by a custom semaphore. It’s like a VIP parking lot but with way more chaos! 🅿️
. Every time a car arrives at a gate, it checks for a free spot. If a spot is open, the car parks. If not, the car waits (or honks in frustration — well, in our imagination). 🚙💨
🚙💨
. Once a car leaves, the spot becomes free, and another car can park. It’s a first-come, first-park madness! 🏁
🛠️ Technologies Used
. Java Threads: To simulate cars arriving at the gates.
. Semaphore: To manage the limited parking spots.
. Synchronization: To ensure no two cars crash... I mean, park in the same spot.
