## Usage ##

SortVisualizer v_._

Welcome to Sorting Visualizer. This app can help users understand what their sorts are doing through visual aid, stepping, and stats logging. It can also be used just for fun.

# Base Features #

Upon entering the program, you will be prompted to enter an array length. This will generate an array with the specified length, but randomly ordered. You can now begin the sort by pressing "Start". The sort will be completed before the runtime of the visualizer so the visualizer can run more smoothly. Now, the visualizer GUI will open, with each bar representing a value, directly correlated to it's height. Upon the sort starting, the bars will begin moving as they sort. A bar highlighted in red indicates the position of the -index pointer- (what value the algorithm is using to compare to) before the swap is made. The user can scale the time and pause the progress as they wish to view specific steps more carefully. Upon completing, the visualizer will highlight each value in green, or scan through the length of the array and progressively scan the chart green, if the algorithm includes a final pass to verify the sorted list. The program is now finished and will display the stats of the sort, and prompt the user to replay the program or close it.

# Upcoming Updates # 

NOTE : Future updates may be implemented in another framework due to graphical limitations of swing. 

For users looking for the simplest interatable example, A visual aid with moving digits on a horizontal plane may be added. This would show what each value is doing, and give visual aids relevant to what the algorithm is doing (Bubble sort will highlight the two indecies, and insertion sort will seperate the sublists, along with showing the index and min value). A raw stats tab may be added a live detailed view, along with an output log. 

# Source Code Overview # 

![image](https://github.com/D-I-N-C/SortingVisualizer/assets/17088609/66cc0976-9950-4c2a-8ff3-d56a27648244)

"f" is the new JFrame instance in main to allow easier and cleaner use of the JFrame proccess. Make JFrame calls using this instance. 

![image](https://github.com/D-I-N-C/SortingVisualizer/assets/17088609/3233104e-3891-4c1d-bc8e-0598e480e1bf)

Initialize the background environment

![image](https://github.com/D-I-N-C/SortingVisualizer/assets/17088609/7d0c8444-4344-44e0-a910-055109abf223)

The documentator has no idea what is going on here. Please come back later...

![image](https://github.com/D-I-N-C/SortingVisualizer/assets/17088609/99b329c6-9189-4871-bd3b-bfb15c7a287f)

Fetch the array length selected by the user

![image](https://github.com/D-I-N-C/SortingVisualizer/assets/17088609/d17b5871-b396-4ad8-abdd-7e12c36573fb)

Make an array with the specific length specified by the user

![image](https://github.com/D-I-N-C/SortingVisualizer/assets/17088609/e43a9710-e95b-4776-8ec5-87b9dc195b28)

Drawing each value of the array as a rectangle.

## IN PROGRESS, PROGRAM IS NOT FINAL ##

This branch is on a new fork of the project.











