import matplotlib.pyplot as plt
import pandas as pd

df = pd.read_csv('stats.csv')
max_delay = df.max()[0]
min_delay = df.min()[0]
mean_delay = df.mean()[0]
print("Max delay (ms):",max_delay)
print("Min delay (ms):",min_delay)
print("Average delay (ms):",mean_delay)
df.plot(xlabel='Samples', ylabel ='Delay (ms)', grid=True, title="Crypto Detector System Performance")
plt.plot([0, len(df)], [mean_delay, mean_delay], 'r--', lw=2)
plt.legend(['Delay (ms)','Average delay (ms)'])
plt.show()
