Мини-проект "Список дел"
=========================

Уровень 1
---------
Данная работа дает *40 баллов* и её целью является изучить основы использования базы данных в приложении на Android. Дополнительно в рамках данной работы вы научитесь создавать и заполнять элементы ListView.

В рамках данной работы вы должны создать приложение "Список дел", состоящее из двух активностей. 

На первой активности необходимо добавить поле ввода и список дел пользователя. В поле ввода пользователь может ввести новое "дело" и добавить его в список (например, по нажатию на кнопку).

Список дел пользователя – список текстовых полей, по нажатие на которое "дело" помечается выполненным и пропадает из списка. 

Данный список дел должен храниться в базе данных. По-сути необходимо создать одну таблицу базы данных: tasks (id: INTEGER, text: TEXT, done: INTEGER).

На второй активности пользователь должен иметь возможность посмотреть все выполенные дела.

### Ссылки
* http://developer.android.com/tools/help/sqlite3.html
* http://developer.android.com/training/basics/data-storage/databases.html
* http://developer.android.com/guide/topics/ui/layout/listview.html
* http://developer.alexanderklimov.ru/android/sqlite/
* http://developer.alexanderklimov.ru/android/listview/

Уровень 2
---------
Данная работа дает ещё *15 баллов*.

Модифицируйте отображение списка таким образом, чтобы непосредственно в списке рядом с каждым текстовым полем отображался checkbox, кликая по которому пользователь "выполняет" дело.

После выполнения этого уровня задания, удалите вторую активность (с выполненными делами). И сделайте так, чтобы в единственной активности отображались все дела, но по наличию "галочки" можно было понять их состояние.

### Ссылки
* http://habrahabr.ru/post/133575/

Уровень 3
---------
Данная работа дает ещё *45 баллов*.

Модифицируйте имеющееся приложение для ведения списка таким образом, чтобы у пользователя была возможность создавать различные списки. Для этого весь текущий код вынесите в отдельный фрагмент. А также создайте ещё один фрагмент для управления списками (создать, удалить). 

Необходимо также создать таблицу в базе данных lists (id INTEGER, name TEXT) и добавить поле (list INTEGER) в таблицу tasks.

### Ссылки
* http://developer.android.com/guide/components/fragments.html
* http://developer.android.com/training/basics/fragments/communicating.html
* http://developer.android.com/training/basics/fragments/index.html
* http://developer.alexanderklimov.ru/android/theory/fragments.php
* http://developer.alexanderklimov.ru/android/views/fragment.php
* http://developer.alexanderklimov.ru/android/theory/fragment-add.php
* http://developer.alexanderklimov.ru/android/theory/fragment-replace.php


