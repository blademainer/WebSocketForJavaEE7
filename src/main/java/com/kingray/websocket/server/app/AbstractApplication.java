/**
 * WebSocketForJavaEE7
 */
package com.kingray.websocket.server.app;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingray.websocket.server.annotation.WebSocketApplication;
import com.kingray.websocket.server.route.WebSocketMessageRouter;
import com.kingray.websocket.vo.VO;
import com.kingray.websocket.vo.WebSocketVo;
import com.xiongyingqi.util.AnnotationHelper;
import com.xiongyingqi.util.EntityHelper;
import com.xiongyingqi.util.PackageUtil;

/**
 * @author xiongyingqi <a href="http://xiongyingqi.com">xiongyingqi.com</a>
 * @version 2014年2月24日 下午2:34:36
 */
public abstract class AbstractApplication<T extends VO> implements Application {
	protected T model;
	protected ObjectMapper objectMapper = new ObjectMapper();
	protected String modelContent;
	/**
	 * 记录登记ID与对应Application的映射表
	 */
	private static Map<String, Class<? extends Application>> registerMap = new HashMap<String, Class<? extends Application>>();
	//	private static Set<Class<?>> applicationClasses = new HashSet<Class<?>>();

	static {
		loadApplications();
	}

	public AbstractApplication() {

	}

	//	public AbstractApplication(String appId) throws RegisterApplicationException {
	//		EntityHelper.print(appId);
	//		Class<? extends AbstractApplication> applicationClass = registerMap.get(appId);
	//		if (applicationClass != null) {
	////			throw new RegisterApplicationException("注册应用失败，ID已被应用注册：" + applicationClass);
	//		} else {
	//			registerMap.put(appId, this.getClass());
	//			EntityHelper.print(this.getClass());
	//		}
	//	}

	public static WebSocketVo doExecute(WebSocketVo webSocketVo) throws NoSuchApplicationException {
		String appId = webSocketVo.getAppId();
		Class<? extends Application> applicationClass = registerMap.get(appId);
		if (applicationClass == null) {
			throw new NoSuchApplicationException("指定的传值对象无法找到！" + webSocketVo.getAppId());
		} else {
			Application application = null;
			try {
				application = applicationClass.newInstance();
				try {
					return tryPrepareExecute(application, webSocketVo);
				} catch (NotAbstractApplicationException e) {
					e.printStackTrace();
					return application.execute(webSocketVo);
				}
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	private static WebSocketVo tryPrepareExecute(Application application, WebSocketVo webSocketVo)
			throws NotAbstractApplicationException {
		if (application.getClass().getSuperclass() != AbstractApplication.class) {
			throw new NotAbstractApplicationException("该类不是使用继承AbstractApplication的方式。");
		}
		AbstractApplication abstractApplication = (AbstractApplication) application;
		return abstractApplication.prepareExecute(webSocketVo);
	}

	protected WebSocketVo prepareExecute(WebSocketVo webSocketVo) {
		this.modelContent = (String) webSocketVo.getContent();
		return this.execute(webSocketVo);
	}
	
	protected String toJson(VO vo) throws JsonProcessingException {
		String json = objectMapper.writeValueAsString(vo);
		return json;
	}

	@SuppressWarnings("unchecked")
	private static void loadApplications() {
		Package[] packages = Package.getPackages();
		for (int i = 0; i < packages.length; i++) {
			Package pkg = packages[i];
			Set<Class<?>> classes = PackageUtil.getclass(pkg);
			for (Class<?> clazz : classes) {
				if (clazz.getSuperclass() == AbstractApplication.class
						|| isContainsInterface(clazz, Application.class)) {
					WebSocketApplication webSocketApplication = AnnotationHelper
							.readAnnotationValue(clazz, WebSocketApplication.class);
					if (webSocketApplication == null) {
						continue;
					}
					String id = webSocketApplication.value();
					if (id == null || "".equals(id.trim())) {
						id = EntityHelper.getClassToBeanName(clazz);
					}
					registerMap.put(id, (Class<? extends Application>) clazz);
				}
			}
		}
	}

	private static boolean isContainsInterface(Class<?> clazz, Class<?> interfaceClass) {
		Class<?>[] interfaces = clazz.getInterfaces();
		for (int i = 0; i < interfaces.length; i++) {
			Class<?> class1 = interfaces[i];
			if (class1 == interfaceClass) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 自动封装model类，前提是必须定义泛型变量的类型 <br>
	 * 2014年2月25日 下午4:32:07
	 * 
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected T getModel() throws JsonParseException, JsonMappingException, IOException {
		Type type = EntityHelper.getGenericTypes(getClass())[0];
		if (model == null && modelContent != null) {
			model = (T) objectMapper.readValue(modelContent, (Class<T>) type);
		}
		return model;
	}

	public static void main(String[] args) {
		//		Type type = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
		//		System.out.println(type);
		String message = "{\"id\":\"0\", \"appId\":\"login\", \"content\":{\"userName\":\"admin\"}}";
		System.out.println(message);
		WebSocketMessageRouter.route(message, null);
		for (int i = 0; i < 10; i++) {
			new Thread() {
				/**
				 * <br>
				 * 2014年2月26日 上午9:36:38
				 * 
				 * @see java.lang.Thread#run()
				 */
				@Override
				public void run() {
					for (int j = 0; j < 10; j++) {
						try {
							Thread.sleep(100);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
//						try {
//							doExecute(new WebSocketVo(k++, "login",
//									"{\"userName\":\"admin\"}", null));
//						} catch (NoSuchApplicationException e) {
//							e.printStackTrace();
//						}
						
					}
				}
			}.start();
		}
	}
	static int k;
}
