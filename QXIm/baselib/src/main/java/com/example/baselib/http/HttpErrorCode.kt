package com.example.baselib.http

/**
 * @author: chenjiayou
 * @createBy: 2019/11/25
 * @descript: 服务器返回错误码
 */
class HttpErrorCode {
    companion object {
        /**
         *  系统异常
         * 1 system error
         */
        const val SYSTEM_ERROR = 1
        /**
         * 参数错误
         * 2 parameter error
         */
        const val PARAMS_ERROR = 2
        /**
         *  业务数据验证未通过
         * 3 business data verification failed
         */
        const val BUSINESS_DATA_VERIFICATION_FAILED = 3
        /**
         *  数据不存在
         * 4 data not exist
         */
        const val DATA_NOT_EXIST = 4
        /**
         *  请重新登录
         * 5 please login again
         */
        const val PLEASE_LOGIN_AGAIN = 5
        /**
         * 没有权限访问
         * 6 no access
         */
        const val NO_ACCESS = 6
        /**
         *  参数绑定错误
         *  7 parameter binding error
         */
        const val PARAMETER_BINDING_ERROR = 7
        /**
         * 签名验证未通过
         * 8  signature verification failed
         */
        const val SIGNATURE_VERIFICATION_FAILED = 8

        /**
         * 服务调用异常
         * 9 system call exception
         */
        const val SERVICE_CALL_EXCEPTION = 9
        /**
         *   数据库操作异常
         * 10 database operation exception
         */
        const val DATABASE_OPERATION_EXCEPTION = 10

        /**
         * 业务异常
         * 100 business exception
         */
        const val BUSINESS_EXCEPTION = 100

        /**
         * 流异常
         * 101 stream exception
         */
        const val STREAM_EXCEPTION = 101

        /**
         * 102 service request timeout, please try again
         */
        const val SERVICE_REQUEST_TIME = 102

        /**
         * 200 OK
         */
        const val OK = 200

        /**
         * 验证码错误
         */
        const val VERIFY_CODE_ERROR = 10001

        /**
         * 登录账号已注销
         * 10100 login account cancelled error code
         */
        const val LOGIN_ACCOUNT_CANCELLED = 10100

        /**
         * 登录账号已冻结
         *10101 login account frozen error code
         */
        const val LOGIN_ACCOUNT_FORZEN = 10101

        /**
         * 登录账号未激活
         * 10102 login account not activated error code
         */
        const val LOGIN_ACCOUNT_NOT_ACTIVATED = 10102

        /**
         *   10103	个人未实名认证	错误码
         * 10103 personal authentication without real name error code
         */
        const val PERSONAL_AUTHENTICATION_WITHOUT_REAL_NAME = 10103

        /**
         *        10104	企业未实名认证	错误码
         *10104 error code of enterprise not real name authentication
         */
        const val ENTERPRISE_NOT_REAL_NAME_AUTHENTICATION = 10104

        /**
         *       10105	合作终止	错误码
         * 10105 cooperation termination error code
         */
        const val COOPERATION_TERMINATION = 10105

        /**
         *    10106	合作到期	错误码
         *    10106 cooperation expiration error code
         */
        const val COOPERATION_EXPRIATION = 10106

        /**
         *   10107	API授权解除	错误码
         *  10107 API authorization release error code
         */
        const val API_AUTHORIZATION = 10107

        /**
         *    10108	API授权冻结	错误码
         *10108 API authorization freeze error code
         */
        const val API_AUTHROIZATION_FREEZE = 10108
        /**
         *   10109	系统主体账号未实名认证	错误码
         *10109 error code of system principal account not real name authentication
         */
        const val SYSTEM_PRINCIPAL_ACCOUNT_NOT_REAL_NAME_AUTHENTICATION = 10109
        /**
         *   10110	系统主体账号已注销	错误码
         *10110 system principal account has been cancelled error code
         */
        const val SYSTEM_PRINCIPAL_ACCOUNT_HAS_BEEN_CANCELLED = 10110
        /**
         *     10111	系统主体账号已冻结	错误码
         *10111 system principal account frozen error code
         */
        const val SYSTEM_PRINCIPAL_ACCOUNT_FORZEN = 10111
        /**
         *      10112	系统主体权限变更	错误码
         * 10112 system principal authority change error code
         */
        const val SYSTEM_PRINCIPAL_AUTHORITY_CHANGE = 10112

        /**
         *    10113	系统主体授权状态解除	错误码
         *10113 system principal authorization status release error code
         */
        const val SYSTEM_PRINCIPAL_AUTHORIZATION_STATUS = 10113
        /**
         *    10114	系统主体授权状态冻结	错误码
         *10114 system principal authorization status freeze error code
         */
        const val SYSTEM_PRINCIPAL_AUTHORIZATION_STATUS_FREEZE = 10114
        /**
         *        10115	登录账号授权状态解除	错误码
         *10115 login account authorization status release error code
         */
        const val LOGIN_ACCOUNT_AUTHORIZATION_STATUS_RELEASE = 10115

        /**
         * 10116	登录账号授权状态冻结	错误码
         *10116 login account authorization status freeze error code
         */
        const val LOGIN_ACCOUNT_AUTHORIZATION_STATUS_FREEZE = 10116

        /**
         *  10117	登录账号权限变更	错误码
         *10117 login account permission change error code
         */
        const val LOGIN_ACCOUNT_PERMISSION_CHANGE = 10117
        /**
         *  10118	您没有该服务/操作的使用权限	错误码
         *10118 you do not have permission to use the service / operation error code
         */
        const val NO_PERMISSION = 10118
        /**
         *         10200	服务已到期，不允许使用	错误码
         *10200 service expired, error code not allowed
         */
        const val SERVICE_EXPIRED = 10200
        /**
         *    10201	服务未开始，不允许使用
         *   10201 service not started, not allowed
         */
        const val SERVICE_NOT_STARTED = 10201
    }
}