import numpy as np
import tensorflow as tf

from include.data import get_data_set_2
from include.model_1 import model
# import sys


def load_label_names():
    return ['airplane', 'automobile', 'bird', 'cat', 'deer', 'dog', 'frog', 'horse', 'ship', 'truck']


def predict(filename):

    # fileName = sys.argv[1]

    # test_x = get_data_set_1()
    test_x = get_data_set_2(filename)

    x, output, y_pred_cls, global_step, learning_rate = model()

    _BATCH_SIZE = 128
    _CLASS_SIZE = 10
    _SAVE_PATH = "./tensorboard/cifar-10-v1.0.0/"

    saver = tf.train.Saver()
    sess = tf.Session()

    # 중요: 예측 하기 전에 텐서플로 graph를 reset 한다.
    tf.reset_default_graph()

    try:
        print("\nTrying to restore last checkpoint ...")
        last_chk_path = tf.train.latest_checkpoint(checkpoint_dir=_SAVE_PATH)
        saver.restore(sess, save_path=last_chk_path)
        print("Restored checkpoint from:", last_chk_path)
    except ValueError:
        print("\nFailed to restore checkpoint. Initializing variables instead.")
        sess.run(tf.global_variables_initializer())

    predicted_class = np.zeros(1, dtype=np.int)
    batch_xs = test_x
    predicted_class[0] = sess.run(y_pred_cls, feed_dict={x: batch_xs})

    print()
    label = load_label_names()
    print("predict : ", label[predicted_class[0]])

    sess.close()

    return label[predicted_class[0]]

# if __name__ == "__main__":
#     main()



