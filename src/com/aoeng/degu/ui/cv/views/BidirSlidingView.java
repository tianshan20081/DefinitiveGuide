/**
 * 双向滑动
 */
package com.aoeng.degu.ui.cv.views;

import android.content.Context;
import android.os.AsyncTask;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.AbsListView.RecyclerListener;
import android.widget.RelativeLayout;

/**
 * @author [Aoeng Zhang] @datatime Aug 31, 2013:8:52:08 PM
 * @Email [zhangshch2008@gmail.com] Aug 31, 2013
 */
public class BidirSlidingView extends RelativeLayout implements OnTouchListener {

	/*
	 * 滚动显示和隐藏左侧布局时，手指滑动需要达到的速度
	 */
	public static final int SNAP_VELOCITY = 200;
	/*
	 * 滑动状态的一种，表示未进行滑动
	 */
	public static final int DO_NOTHING = 0;
	/*
	 * 滑动状态的一种，表示正在滑出左侧菜单
	 */
	public static final int SHOW_LEFT_MENU = 1;
	/*
	 * 滑动状态的一种，表示正在滑出右侧菜单
	 */
	public static final int SHOW_RIGHT_MENU = 2;
	/*
	 * 滑动状态的一种，表示正在隐藏左侧菜单
	 */
	public static final int HIDE_LEFT_MENU = 3;
	/*
	 * 滑动状态的一种，表示正在隐藏右侧菜单
	 */
	public static final int HIDE_RIGHT_MENU = 4;
	/*
	 * 记录当前的滑动状态
	 */
	private int slideSatet;
	/*
	 * 屏幕宽度值
	 */
	private int screenWidth;
	/*
	 * 再被判定为滑动之前用户的手指可以移动的最大值
	 */
	private int touchSlop;
	/*
	 * 记录手指按下时的横坐标
	 */
	private float xDown;
	/*
	 * 记录手指按下时的纵坐标
	 */
	private float yDown;
	/*
	 * 手指移动时的横坐标
	 */
	private float xMove;
	/*
	 * 手指移动时的纵坐标
	 */
	private float yMove;
	/*
	 * 记录手指抬起时横坐标
	 */
	private float xUp;
	/*
	 * 左侧菜单当前是显示还是隐藏。只有完全显示或隐藏时才会更改此值，华东过程中此值无效
	 */
	private boolean isLeftMenuVisiable;
	/*
	 * 右侧菜单当前是显示还是隐藏。只有完全显示或隐藏时才会更改此值，华东过程中此值无效
	 */
	private boolean isRightMenuVisiable;
	/*
	 * 是否在滑动
	 */
	private boolean isSliding;
	/*
	 * 左侧布局对象
	 */
	private View leftMenuLayout;
	/*
	 * 右侧布局对象
	 */
	private View rightMenuLayout;
	/*
	 * 内容布局对象
	 */
	private View contentLayout;
	/*
	 * 用于监听滑动事件的View
	 */
	private View mBindView;
	/*
	 * 右侧菜单布局的参数
	 */
	private MarginLayoutParams rightMenuLayoutParams;
	/*
	 * 左侧菜单布局参数
	 */
	private MarginLayoutParams leftMenuLayoutParams;
	/*
	 * 内容布局参数
	 */
	private RelativeLayout.LayoutParams contentLayoutParams;
	/*
	 * 用于计算手指滑动的速度
	 */
	private VelocityTracker mVelocityTracker;

	/**
	 * 重写 BidirSlidingUI 的构造函数，其中获取的屏幕的宽度和 touchSlop 的值
	 * 
	 * @param context
	 */
	public BidirSlidingView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	/**
	 * 绑定监听滑动事件的 View
	 * 
	 * @param bindView
	 *            需要绑定的 View 对象
	 */
	public void setScrollEvent(View bindView) {
		mBindView = bindView;
		mBindView.setOnTouchListener(this);
	}

	/*
	 * 将界面滚动到左侧菜单界面，滚动速度设定为 -30
	 */
	public void scrollToLeftMenu() {
		new LeftMenuScrollTask().execute(-30);
	}

	/*
	 * 将界面滑动到右侧菜单界面，滚动速度设定为 -30
	 */
	public void scrollToRight() {
		new RightMenuScrollTask().execute(-30);
	}

	/*
	 * 将界面从右侧菜单滚动到内容界面，滚动速度设定为 30
	 */
	public void scrollToContentFromRightMenu() {
		new RightMenuScrollTask().execute(30);
	}

	/**
	 * 左侧菜单是否左侧菜单是否完全显示出来，滑动过程中此值无效
	 * 
	 * @return 左侧菜单完全显示 返回 true ,否则返回 false
	 */
	public boolean isLeftLayoutVisiable() {
		return isLeftMenuVisiable;
	}

	/**
	 * 右侧菜单是否左侧菜单是否完全显示出来，滑动过程中此值无效
	 * 
	 * @return 右侧菜单完全显示 返回 true ,否则返回 false
	 */
	public boolean isRightLayoutVisiable() {
		return isRightMenuVisiable;
	}

	/*
	 * (non-Javadoc) 在 onLayout(） 中重新设定左侧菜单，右侧菜单，以及内容布局的参数
	 * 
	 * @see android.widget.RelativeLayout#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			// 获取左侧菜单布局对象
			leftMenuLayout = getChildAt(0);
			leftMenuLayoutParams = (MarginLayoutParams) leftMenuLayout.getLayoutParams();

			// 获取右侧菜单布局对象
			rightMenuLayout = getChildAt(1);
			rightMenuLayoutParams = (MarginLayoutParams) rightMenuLayout.getLayoutParams();
			// 获取内容布局对象
			contentLayout = getChildAt(2);
			contentLayoutParams = (LayoutParams) contentLayout.getLayoutParams();
			contentLayoutParams.width = screenWidth;
			contentLayout.setLayoutParams(contentLayoutParams);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnTouchListener#onTouch(android.view.View,
	 * android.view.MotionEvent)
	 */
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		createVelocityTracker(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// 手指按下时，记录按下时的坐标
			xDown = event.getRawX();
			yDown = event.getRawY();
			// 将滑动状态初始化为 DO_NOTHING
			slideSatet = DO_NOTHING;
			break;
		case MotionEvent.ACTION_MOVE:
			xMove = event.getRawX();
			yMove = event.getRawY();
			// 手指移动时，对比按下时的坐标，计算出移动的距离
			int moveDistanceX = (int) (xMove - xDown);
			int moveDistanceY = (int) (yMove - yDown);
			// 检查当前的滑动状态
			checkSlideState(moveDistanceX, moveDistanceY);
			// 根据当前滑动滑动状态决定如何偏移内容布局
			switch (slideSatet) {
			case SHOW_LEFT_MENU:
				contentLayoutParams.rightMargin = -moveDistanceX;
				checkLeftMenuBorder();
				contentLayout.setLayoutParams(contentLayoutParams);
				break;
			case HIDE_LEFT_MENU:
				contentLayoutParams.rightMargin = -leftMenuLayoutParams.width - moveDistanceX;
				checkLeftMenuBorder();
				contentLayout.setLayoutParams(contentLayoutParams);
				break;
			case SHOW_RIGHT_MENU:
				contentLayoutParams.leftMargin = moveDistanceX;
				checkRightMenuBorder();
				contentLayout.setLayoutParams(contentLayoutParams);
				break;
			case HIDE_RIGHT_MENU:
				contentLayoutParams.leftMargin = -rightMenuLayoutParams.width + moveDistanceX;
				checkRightMenuBorder();
				contentLayout.setLayoutParams(contentLayoutParams);
				break;
			default:
				break;
			}
			break;
		case MotionEvent.ACTION_UP:
			xUp = event.getRawX();
			int upDistanceX = (int) (xUp - xDown);
			if (isSliding) {
				// 手指抬起时，进行判断当前手势的意图
				switch (slideSatet) {
				case SHOW_LEFT_MENU:
					if (shouldScrollToLeftMenu()) {
						scrollToLeftMenu();
					} else {
						scrollToContentFromLeftMenu();
					}
					break;
				case HIDE_LEFT_MENU:
					if (shouldScrollToContentFromLeftMenu()) {
						scrollToContentFromLeftMenu();
					} else {
						scrollToLeftMenu();
					}
					break;
				case SHOW_RIGHT_MENU:
					if (shouldScrollToRightMenu()) {
						scrollToRightMenu();
					} else {
						scrollToContentFromRightMenu();
					}
					break;
				case HIDE_RIGHT_MENU:
					if (shouldScrollToContentFromRightMenu()) {
						scrollToContentFromRightMenu();
					} else {
						scrollToRightMenu();
					}
					break;
				}
			} else if (upDistanceX < touchSlop && isLeftMenuVisiable) {
				// 当左侧菜单显示时，如果用户点击一下内容部分，则直接滚动到内容界面
				scrollToContentFromLeftMenu();
			} else if (upDistanceX < touchSlop && isRightMenuVisiable) {
				// 当右侧菜单显示时，如果用户点击一下内容部分，则直接滚动到内容界面
				scrollToContentFromRightMenu();
			}
			recycleVelocityTracker();
			break;
		}
		if (v.isEnabled()) {
			if (isSliding) {
				// 正在滑动时让控件得不到焦点
				unFocusBindView();
				return true;
			}
			if (isLeftMenuVisiable || isRightMenuVisiable) {
				// 当左侧或右侧布局显示时，将绑定控件的时间屏蔽掉
				return true;
			}
			return false;
		}
		return true;
	}

	/**
	 * 创建 VelocityTracker 对象，并将触摸事件加入到 VelocityTracker 当中
	 * 
	 * @param event
	 */
	private void createVelocityTracker(MotionEvent event) {
		// TODO Auto-generated method stub
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	/**
	 * 判断是否应该从左侧菜单滚动到内容布局。如果手指移动距离大于左侧菜单宽度的 1/2 或者手指移动速度大于 SNAP_VELOCITY
	 * 就认为应该从左侧菜单滚动到内容布局
	 * 
	 * @return 如果应该从左侧菜单滚动到内容布局返回 true，否则返回 false
	 */
	private boolean shouldScrollToContentFromLeftMenu() {
		// TODO Auto-generated method stub

		return xDown - xUp > leftMenuLayoutParams.width / 2 || getScollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * 使用可以获得焦点的的控件在滑动的时候失去焦点
	 */
	private void unFocusBindView() {
		// TODO Auto-generated method stub
		if (mBindView != null) {
			mBindView.setPressed(false);
			mBindView.setFocusable(false);
			mBindView.setFocusableInTouchMode(false);
		}
	}

	/**
	 * recyle VelocityTracker Object
	 */
	private void recycleVelocityTracker() {
		// TODO Auto-generated method stub
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}

	/**
	 * 判断是否是从右侧菜单滚动到内容布局，如果手指移动距离大雨右侧菜单的 1/2 或者手指移动大于 SNAP_VELOCITY
	 * 就认为应该从右侧菜单滚动到内容布局
	 * 
	 * @return 如果应该从右侧菜单滚动到内容布局，则返回 true，否则 返回 false
	 */
	private boolean shouldScrollToContentFromRightMenu() {
		// TODO Auto-generated method stub
		return xUp - xDown > rightMenuLayoutParams.width / 2 || getScollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * 
	 */
	private void scrollToRightMenu() {
		// TODO Auto-generated method stub

	}

	/**
	 * 判断是否应该滚动将右侧菜单展示出来。如果手指移动距离大于左侧菜单宽度的 1/2 。或者手指移动速度大于
	 * SNAP_VELOCITY,就认为应该滚动将右侧菜单展示出来
	 * 
	 * @return 如果应该将右侧菜单展示出来 返回 true ,否则返回 false
	 */
	private boolean shouldScrollToRightMenu() {
		// TODO Auto-generated method stub
		return xDown - xUp > rightMenuLayoutParams.width / 2 || getScollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * 
	 */
	private void scrollToContentFromLeftMenu() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return
	 */
	private boolean shouldScrollToContentMenu() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 判断是否应该滚动将左侧菜单展示出来。如果手指移动距离大于左侧菜单宽度的 1/2 。或者手指移动速度大于
	 * SNAP_VELOCITY,就认为应该滚动将左侧菜单展示出来
	 * 
	 * @return 如果应该将左侧菜单展示出来 返回 true ,否则返回 false
	 */
	private boolean shouldScrollToLeftMenu() {
		// TODO Auto-generated method stub
		return xUp - xDown > leftMenuLayoutParams.width / 2 || getScollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * 获取手指在绑定布局上的滑动速度
	 * 
	 * @return 滑动速度，以每秒钟移动了多少像素值为单位
	 */
	private int getScollVelocity() {
		// TODO Auto-generated method stub
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}

	/**
	 * 滑动过程中检查右侧菜单的边界值，防止绑定布局滑出屏幕
	 */
	private void checkRightMenuBorder() {
		// TODO Auto-generated method stub
		if (contentLayoutParams.leftMargin > 0) {
			contentLayoutParams.leftMargin = 0;
		} else if (contentLayoutParams.leftMargin < -rightMenuLayoutParams.width) {
			contentLayoutParams.leftMargin = -rightMenuLayoutParams.width;
		}
	}

	/**
	 * 在滑动过程中检查左侧菜单的边界， 防止绑定布局滑动出屏幕
	 */
	private void checkLeftMenuBorder() {
		// TODO Auto-generated method stub
		if (contentLayoutParams.rightMargin > 0) {
			contentLayoutParams.rightMargin = 0;
		} else if (contentLayoutParams.rightMargin < -leftMenuLayoutParams.width) {
			contentLayoutParams.rightMargin = -leftMenuLayoutParams.width;
		}
	}

	/**
	 * 根据手指移动的的距离，判断当前用户的滑动意图，然后给 slideState 复制成相应的滑动状态值
	 * 
	 * @param moveDistanceX
	 *            横向滑动的距离
	 * @param moveDistanceY
	 *            纵向滑动的距离
	 */
	private void checkSlideState(int moveDistanceX, int moveDistanceY) {
		// TODO Auto-generated method stub
		if (isLeftMenuVisiable) {
			if (!isSliding && Math.abs(moveDistanceX) >= touchSlop && moveDistanceX < 0) {
				isSliding = true;
				slideSatet = HIDE_LEFT_MENU;
			}
		} else if (isRightMenuVisiable) {
			if (!isSliding && Math.abs(moveDistanceX) >= touchSlop && moveDistanceX > 0) {
				isSliding = true;
				slideSatet = HIDE_RIGHT_MENU;
			}
		} else {
			if (!isSliding && Math.abs(moveDistanceX) >= touchSlop && moveDistanceX > 0) {
				isSliding = true;
				slideSatet = SHOW_LEFT_MENU;
				contentLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
				contentLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				contentLayout.setLayoutParams(contentLayoutParams);
				// 如果用户想要滑动右侧菜单，将右侧菜单显示，左侧菜单隐藏
				rightMenuLayout.setVisibility(View.VISIBLE);
				leftMenuLayout.setVisibility(View.GONE);
			}
		}

	}

	class LeftMenuScrollTask extends AsyncTask<Integer, Integer, Integer> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Integer doInBackground(Integer... speed) {
			// TODO Auto-generated method stub
			int rightMargin = contentLayoutParams.rightMargin;
			// 根据传入的速度来滚动界面，当滚动到达边界值时，跳出循环
			while (true) {
				rightMargin = rightMargin + speed[0];
				if (rightMargin < -leftMenuLayoutParams.width) {
					rightMargin = -leftMenuLayoutParams.width;
					break;
				}
				if (rightMargin > 0) {
					rightMargin = 0;
					break;
				}
				publishProgress(rightMargin);
				// 为了要有滚动效果，每次循环线程睡眠一段时间，这样肉眼才能够看到滚动动画
				sleep(15);
			}
			if (speed[0] > 0) {
				isLeftMenuVisiable = false;
			} else {
				isLeftMenuVisiable = true;
			}
			isSliding = false;
			return rightMargin;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
		 */
		@Override
		protected void onProgressUpdate(Integer... rightMargin) {
			// TODO Auto-generated method stub
			contentLayoutParams.rightMargin = rightMargin[0];
			contentLayout.setLayoutParams(contentLayoutParams);
			unFocusBindView();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Integer rightMargin) {
			// TODO Auto-generated method stub
			contentLayoutParams.rightMargin = rightMargin;
			contentLayout.setLayoutParams(contentLayoutParams);
		}
	}

	class RightMenuScrollTask extends AsyncTask<Integer, Integer, Integer> {

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected Integer doInBackground(Integer... speed) {
			// TODO Auto-generated method stub
			int leftMargin = contentLayoutParams.leftMargin;
			// 根据传入的速度来滚动界面，当滚动到达边界值时，跳出循环
			while (true) {
				leftMargin = leftMargin + speed[0];
				if (leftMargin < -rightMenuLayoutParams.width) {
					leftMargin = -leftMenuLayoutParams.width;
					break;
				}
				if (leftMargin > 0) {
					leftMargin = 0;
					break;
				}
				publishProgress(leftMargin);
				sleep(15);
			}
			if (speed[0] > 0) {
				isRightMenuVisiable = true;
			} else {
				isLeftMenuVisiable = false;
			}
			isSliding = false;
			return leftMargin;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onProgressUpdate(Progress[])
		 */
		@Override
		protected void onProgressUpdate(Integer... leftMargin) {
			// TODO Auto-generated method stub
			contentLayoutParams.leftMargin = leftMargin[0];
			contentLayout.setLayoutParams(contentLayoutParams);
			unFocusBindView();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(Integer leftMargin) {
			// TODO Auto-generated method stub
			contentLayoutParams.leftMargin = leftMargin;
			contentLayout.setLayoutParams(contentLayoutParams);
		}
	}

	/**
	 * 是当前线程睡眠指定时间，以毫秒为单位
	 * 
	 * @param i
	 *            是定当前线程睡眠多久，以毫秒为单位
	 */
	public void sleep(int i) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(i);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
