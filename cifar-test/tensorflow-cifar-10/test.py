import pickle
import numpy as np
import matplotlib.pyplot as plt
# %matplotlib inline

def unpickle(file):
    fo = open(file, 'rb')
    dict = pickle.load(fo, encoding='latin1')
    fo.close()
    return dict

def grayscale(a):
    return a.reshape(a.shape[0], 3, 32, 32).mean(1).reshape(a.shape[0], -1)

def data_show(data, data_len):
    plt.rcParams['figure.figsize'] = (2, 2)
    for i in range(data_len):
        img = np.reshape(data[i,:], (32, 32))
        plt.subplot(1, data_len, i+1)
        # plt.savefig("./data_set/cifar_10/test" + "_" + str(i) + ".png")
        plt.imshow(img, cmap='gray')

test_data = unpickle('./data_set/cifar_10/test_batch')

# print(test_data['data'])
# print(np.shape(test_data['data']))

test_x = grayscale(test_data['data'])

test_labels = np.array(test_data['labels'])

test_1 = test_x[13].reshape(1, np.shape(test_x[13])[0])
print(np.shape(test_1))
data_show(test_x, 1)


# for i in range(5):
#     img = X_train[i]
#     axarr[i].imshow(img)
# plt.show()