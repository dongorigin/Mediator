package cn.dong.mediator.checker

import javax.lang.model.element.*
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.TypeMirror
import javax.lang.model.util.Elements

fun Element.findAnnotationMirror(annotationClass: Class<*>): AnnotationMirror? {
    return annotationMirrors // 不包括继承的
        .find { it.annotationType.toString() == annotationClass.name }
}

fun AnnotationMirror.findAnnotationValue(key: String): AnnotationValue? {
    return elementValues.entries // 不包括默认值
        .find { it.key.simpleName.toString() == key }
        ?.value
}

fun AnnotationValue.getClasses(): List<TypeMirror> {
    return (value as List<AnnotationValue>).map { it.value as TypeMirror }
}

/** 包括继承的注解 */
fun Element.findAllAnnotationMirror(annotationClass: Class<*>, elementUtils: Elements)
        : AnnotationMirror? {
    return elementUtils.getAllAnnotationMirrors(this) // 包括继承的，内部实现就是循环找父类
        .find { it.annotationType.toString() == annotationClass.name }
}

/** 包括注解默认值 */
fun AnnotationMirror.findAnnotationValueWithDefaults(key: String, elementUtils: Elements)
        : AnnotationValue? {
    return elementUtils.getElementValuesWithDefaults(this).entries // 包括默认值
        .find { it.key.simpleName.toString() == key }
        ?.value
}

fun Element.isMethod(): Boolean = kind == ElementKind.METHOD

fun <A : Annotation> Element.isAnnotationPresent(annotationClass: Class<A>): Boolean {
    return this.getAnnotation(annotationClass) != null
}

fun Element.getDeclaredTypeElement() : TypeElement {
    return (asType() as DeclaredType).asElement() as TypeElement
}
