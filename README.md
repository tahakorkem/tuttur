# tuttur
Tuttur (which means `bind [verb]`  in Turkish) is lighweight library that facilitates the use of DataBinding with Activity, Fragment, Dialog, BottomSheet, and especiallly ListAdapter. Currently, tuttur works with only DataBinding. So, if you use ViewBinding or Compose, this library is useless for you.

Tuttur allows you to code simpler while using DataBinding and also reduces some boilerplates for you. The main idea is simple: Make all your Activity, Fragment, Dialog, BottomSheet and ListAdapter inherit from its Base version. For instance, you have an Activity. Don't inherit from AppCompatActivity, inherit from BaseActivity instead and specify suitable ViewDataBinding class and its layout resource id. Anymore, you have "binding" property in the Activity without needing any implementation else.

## To-do
- Support for ViewBinding
- Separation of ViewBinding and DataBinding into two different modules

## Guide
* [Installation](#installation)
* [Demo app](#demo-app)
* [Usage](#usage)
  * [Activity](#activity)
  * [Fragment](#fragment)
  * [Dialog](#dialog)
  * [BottomSheet](#bottomsheet)
  * [ListAdapter](#listadapter)
    * [Single View Type ListAdapter](#single-view-type-listadapter)
    * [Filterable ListAdapter](#filterable-listadapter)
    * [Multiple View Type ListAdapter](#multiple-view-type-listadapter)
* [Conclusion](#conclusion)
* [Contributing](#contributing)

## Installation:

##### 1) Add this code in your project level build.gradle if it is not added:

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

##### 2) Add this dependency in your app build.gradle:

```gradle
implementation 'com.github.tahakorkem:tuttur:0.1.0'
```

### Note:
Make sure you are using DataBinding:

```gradle
android {
    buildFeatures {
        dataBinding true
    }
}
```

## Demo app

You can find a demo app [here](app) about all the features implemented in this library.

<p align="center">
  <img src="https://user-images.githubusercontent.com/65918918/184905824-96c7e113-b2a0-45e2-8092-c353d2418dcd.png" width="300px"/>
  <img src="https://user-images.githubusercontent.com/65918918/184906171-e6a346e5-d49c-4952-a588-0627e253b5e0.png" width="300px"/>
  <img src="https://user-images.githubusercontent.com/65918918/184898309-3f322758-04e7-44b0-98d1-3420189a8038.png" width="300px"/>
  <img src="https://user-images.githubusercontent.com/65918918/184898466-65273d5d-6bdd-4e99-92b8-5b6334c15220.png" width="300px"/>
  <img src="https://user-images.githubusercontent.com/65918918/184898493-9703abba-8874-4acb-a35b-44a8a8415796.png" width="300px"/>
</p>

## Usage

Remember that every class you need starts with `Base`, such as `BaseActivity`, `BaseFragment`, `BaseDialogFragment` etc. Let's look at some details!

### Activity

Your activity must inherit from `BaseActivity` and you must specify your ViewDataBinding class within angle brackets and pass layout resource id in constructor. It is important to note that you must use matching ViewDataBinding class and layout resource id.

```kotlin
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // "binding" property is initialized here.
    }
    
}
```

### Fragment

Your fragment must inherit from `BaseFragment` and you must specify your ViewDataBinding class within angle brackets and pass layout resource id in constructor.

```kotlin
class MovieListFragment : BaseFragment<FragmentMovieListBinding>(R.layout.fragment_movie_list) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // "binding" property is available here.
    }
}
```

### Dialog

Your dialog must inherit from `BaseDialogFragment` and you must specify your ViewDataBinding class within angle brackets and pass layout resource id in constructor.

```kotlin
class PostDetailDialog : BaseDialogFragment<DialogPostDetailBinding>(R.layout.dialog_post_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ThemeDialog) // You can set custom dialog style here.
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // "binding" property is available here.
    }
    
}
```

### BottomSheet

Your bottom sheet must inherit from `BaseBottomSheetDialogFragment` and you must specify your ViewDataBinding class within angle brackets and pass layout resource id in constructor.

```kotlin
class MovieBottomSheet : BaseBottomSheetDialogFragment<BottomSheetMovieBinding>(R.layout.bottom_sheet_movie) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // "binding" property is available here.
        // you can also access "behaviour" property which is BottomSheetBehavior of your BottomSheet
        behaviour.state = BottomSheetBehavior.STATE_EXPANDED
    }
    
}
```

### ListAdapter

Primarily, your model classes you use in ListAdapter must inherit from `Identifiable` that has `id: Any` property, `equals()` and `hashCode()` member functions. This is required to identificate each item in the list and detect value changes. Remember that using Kotlin `data class`es let you automatically implement `equals()` and `hashCode()` functions.

```kotlin
data class Movie(
    override val id: Int,
    val title: String,
    val description: String,
    val rating: Float,
) : Identifiable
```

Secondly, if you will have only one view type in your ListAdapter, you can use `BaseListAdapter`. If you have multiple view types in your ListAdapter, you can choose one of them to suit your use case: `Base2ViewListAdapter`, `Base3ViewListAdapter`, `Base4ViewListAdapter`, `Base5ViewListAdapter`, or `BaseMultiViewListAdapter`.

#### Single View Type ListAdapter

Your adapter must inherit from `BaseListAdapter` and you must specify both your model class and ViewDataBinding class and pass layout resource id in constructor. You can override `onBind` and `onCreate` in your adapter instead of  overriding `onBindViewHolder` and `onCreateViewHolder` respectively.

```kotlin
class MovieAdapter(
    private val callback: MovieCallback // You can pass any data to your adapter if needed
) : BaseListAdapter<Movie, ListItemMovieBinding>(R.layout.list_item_movie) {

    override fun ListItemMovieBinding.onBind(
        holder: BaseViewHolder<ListItemMovieBinding>,
        index: Int,
        item: Movie
    ) {
        movie = item
        callback = this@MovieAdapter.callback
        // executePendingBindings() will be called after this function automatically
    }

}
```

#### Filterable ListAdapter

Its implementation is same as above except you must override `onFilter()` function in your adapter. You can call `filter()` function from outside when you want to filter your list.

```kotlin
class CountryAdapter : BaseFilterableListAdapter<Country, ListItemCountryBinding>(R.layout.list_item_country) {

    override fun ListItemCountryBinding.onBind(
        holder: BaseViewHolder<ListItemCountryBinding>,
        index: Int,
        item: Country
    ) {
        country = item
    }

    override fun List<Country>.onFilter(constraint: String?): List<Country> {
        return filter { it.name.contains(constraint ?: "", ignoreCase = true) }
    }

}
```

#### Multiple View Type ListAdapter

In cases where you have multiple view types in your ListAdapter, it is important and useful to have type safety in both item models and ViewDataBinding classes. To provide that functionality, you can use multi view BaseListAdapters.

You must inherit from `Base2ViewListAdapter`, `Base3ViewListAdapter`, `Base4ViewListAdapter`, or `Base5ViewListAdapter` if you have at most five view types. To use more than five view types, you must inherit from `BaseMultiViewListAdapter`. Their implementation is similar to single view type ListAdapter except you must specify more than one model classes and ViewDataBinding classes. Additionally, you can override multiple `onBindN` and `onCreateN` functions in your adapter up to five view types. In the use of `BaseMultiViewListAdapter`, you can override only an `onBind` and `onCreate` function and you must struggle with different view types yourself.

It is useful to note that your all model classes must inherit from same parent in order to provide type safety.

```kotlin
sealed interface PostItem : Identifiable {
    override val id: Int
}

interface Post : PostItem {
    val author: User
    val content: String?

    data class Text(
        override val id: Int,
        override val author: User,
        override val content: String
    ) : Post

    data class Image(
        override val id: Int,
        override val author: User,
        override val content: String?,
        val imageUrl: String
    ) : Post
}

data class SectionHeader(
    override val id: Int,
    val title: String
) : PostItem

object Footer : PostItem {
    override val id: Int = Int.MAX_VALUE
    override fun equals(other: Any?) = other is Footer
    override fun hashCode() = 0
}
```

For instance, you have four view types in your ListAdapter for `Post.Text`, `Post.Image`, `SectionHeader`, and `Footer` and theirs ViewDataBinding classes. You must use `Base4ViewListAdapter` since you have four view types. Generic type parameters must be in that order: Parent model class (`PostItem` in this particular case), 1st model class, 1st ViewDataBinding class, 2nd model class, 2nd ViewDataBinding class, etc. You must also pass layout resource ids in constructor in form of `Bindable` for example as `Bindable(Post.Image::class, R.layout.list_item_text_post)` or `bind(R.layout.list_item_text_post)` in the shorthand form.

```kotlin
class PostAdapter
: Base4ViewListAdapter<PostItem, Post.Text, ListItemTextPostBinding, Post.Image, ListItemImagePostBinding, SectionHeader, ListItemSectionHeaderBinding, Footer, ListItemFooterBinding>(
    bind(R.layout.list_item_text_post),
    bind(R.layout.list_item_image_post),
    bind(R.layout.list_item_section_header),
    bind(R.layout.list_item_footer)
) {

    override fun ListItemTextPostBinding.onBind1(
        holder: BaseViewHolder<ListItemTextPostBinding>,
        index: Int,
        item: Post.Text
    ) {
        textPost = item
    }

    override fun ListItemImagePostBinding.onBind2(
        holder: BaseViewHolder<ListItemImagePostBinding>,
        index: Int,
        item: Post.Image
    ) {
        imagePost = item
        textViewContent.isVisible = item.content != null
    }

    override fun ListItemSectionHeaderBinding.onBind3(
        holder: BaseViewHolder<ListItemSectionHeaderBinding>,
        index: Int,
        item: SectionHeader
    ) {
        sectionHeader = item
    }
}
```

## Conclusion

To conclude, tuttur can be useful library if you use its major features. Especially, this library make you feel more comfortable whilst you are dealing with multi-view type ListAdapters.

## Contributing

You can contribute to this library by fixing any issues you encountered or adding more functionalities. Fell free to open pull requests.
