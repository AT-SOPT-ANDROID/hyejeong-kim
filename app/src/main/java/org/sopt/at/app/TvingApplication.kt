package org.sopt.at.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
* 애플리케이션 수준 종속 항목 컨테이너 역할을 하는 애플리케이션의 기본 클래스를 비롯하여 Hilt의 코드 생성을 트리거
* Application 객체의 수명 주기에 연결되며 이와 관련한 종속 항목 제공
*/
@HiltAndroidApp
class TvingApplication : Application() {
}