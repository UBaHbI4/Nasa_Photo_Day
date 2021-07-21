# Nasa Photo Day - приложение фото дня
## Факультет: Android-разработки<br>Курс: Android. Material design Дата начала: 28.06.2021   
---
### Домашнее задание №1 
- Изучите API NASA, который мы будем использовать в течение курса, и продумайте, как вы выстроите своё приложение, какие там будут экраны.
- Создайте первый экран приложения с фотографией дня.
- Добавьте описание (приходит с сервера) под фотографией в виде текстовой подписи или в виде BottomSheet.
- Добавьте текстовое поле для поиска неизвестных слов в «Википедии».
- Добавьте адаптивную иконку для вашего приложения.
- * Имплементируйте нижнюю навигацию. 
- * Определитесь с архитектурой вашего приложения (MVVM, MVP, Clean).
- ** Добавьте чипы над фото для сегодняшней, вчерашней и позавчерашней фотографии (пример запроса) или для фото в формате HD. По нажатию на соответствующий чип загружайте соответствующие данные и обновляйте их на экране. API Picture of the Day. 
### Домашнее задание №2 
- Создайте стили для ваших компонентов и применяйте эти стили.
- Создайте две темы для вашего приложения и в отдельном экране настроек добавьте возможность менять эти темы. Тем может быть больше двух, и это не обязательно тёмная и светлая темы. Это могут быть, например, космическая, лунная, марсианская темы.
- Создайте свой уникальный стиль для приложения и его виджетов.
- * Используйте свои шрифты в приложении.
- * Примените автоматическую тёмную тему для девайсов на 10+ Android.
### Домашнее задание №3 
- Теперь вы знаете про навигацию всё. Определитесь с навигацией в вашем приложении. В нём сейчас есть главный экран с фото дня и выход в «Википедию», условный экран настроек и заглушки для экранов с фото Земли, Марса, погоды. Но вы можете отображать всё или что-то из следующего: 
 1) фото текущего дня, вчерашнего дня, выбранной даты (в высоком и низком разрешении);
 2) фото Земли сегодня или за любой день;
 3) фото Марса и погоду на нём;
 4) фото Луны в высоком разрешении;
 5) космическую погоду;
 6) сведения о метеоритах, наиболее близких к Земле;
 7) любые иные данные и фото, доступные благодаря обширному API;
 8) экран настроек приложения.
- Навигацию по экранам можно осуществлять различными способами или комбинировать их:
 1) классический NavigationDrawer;
 2) классический AppBar и меню сверху;
 3) AppBar снизу;
 4) BottomNavigationView;
 5) TabLayout + ViewPager.
- После создания экранов и навигации между ними добавьте функционал для работы с выбранными API. Фото дня вы уже скачиваете, напишите по аналогии остальные.
- * Добавьте кастомную промотку экранов из примера в документации.
- * Посмотрите, какие есть атрибуты у TabLayout, которые вы можете использовать в XML для украшения вкладок.
- * Примените свою тему для BottomNavigationView.
- ** Иногда с сервера приходит ссылка не на фотографию, а на ролик на YouTube или Vimeo формата "https://www.youtube.com/embed/PpyPgJHKxSw?rel=0". Вместо отображения ошибки отображайте или сам видеоролик, или ссылку, которую можно открыть в браузере или соответствующем приложении.
### Домашнее задание №4 
- Примените знания, полученные о ConstraintLayout, в своём проекте.
- Если дизайн и архитектура вашего приложения позволяют, добавьте CollapsingToolbarLayout на какой-либо экран (или FAB) и добавьте для него анимацию сворачивания/разворачивания или какое-то кастомное поведение. Поэкспериментируйте с параметрами snap, enterAlways, collapse и другими.
- Добавьте анимацию элементов в вашем приложении, где это уместно. Или придумайте новый вариант взаимодействия с приложением с помощью MotionLayout.
- * Посмотрите, как можно создавать анимации с помощью ConstraintSet;
- * Создайте анимацию, когда по свайпу иконки «Википедии» справа-налево за ней выезжает окно поиска и скрывается обратно по свайпу иконки слева-направо;
- ** Попробуйте создать свою анимацию по аналогии с этой.
### Домашнее задание №5
- Примените анимации в проекте там, где это уместно.
- * Анимируйте появление данных на экранах вашего приложения, добавьте анимацию увеличения фотографий по тапу на них. 
- ** Изучите переходы между экранами.
### Домашнее задание №6
- Добавьте в своё приложение экран со списком дел, в котором пользователь сможет создавать заметки, редактировать их или перемещать относительно друг друга. Примените для этого полученные на уроке знания.
- * Добавьте назначение приоритета заметкам.
- * Добавьте поиск/фильтрацию заметок по ключевым словам, приоритетам, дате создания и обновляйте список в соответствии с результатом поиска/фильтрации.