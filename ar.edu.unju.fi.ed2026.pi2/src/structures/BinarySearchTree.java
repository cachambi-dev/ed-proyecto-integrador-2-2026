package structures;

//
// Created by Julio Tentor <jtentor@fi.unju.edu.ar>
//

/** Implementa un árbol binario de búsqueda ordenado por comparación natural. */
public class BinarySearchTree<ELEMENT extends Comparable<ELEMENT>> extends BinaryTree<ELEMENT> {

    /** Crea un árbol binario de búsqueda vacío. */
    public BinarySearchTree() {
        super();
    }

    /**
     * Inserta un elemento manteniendo el orden del árbol.
     * Los elementos equivalentes se colocan en el subárbol derecho.
     *
     * @param item elemento que se insertará
     */
    public void add(ELEMENT item) {
        if (this.root == null) {
            this.root = new BTNode<ELEMENT>(item, null, null);
        } else {
            BTNode<ELEMENT> temp = this.root;
            BTNode<ELEMENT> prev = null;
            while (temp != null) {
                prev = temp;
                if (item.compareTo(temp.item) < 0) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
            temp = new BTNode<ELEMENT>(item, null, null);
            if (item.compareTo(prev.item) < 0) {
                prev.left = temp;
            } else {
                prev.right = temp;
            }
        }
    }

    /**
     * Busca y devuelve un elemento equivalente.
     *
     * @param item elemento utilizado como clave de búsqueda
     * @return elemento encontrado, o {@code null} si no existe
     */
    public ELEMENT buscar(ELEMENT item) {
        BTNode<ELEMENT> current = this.root;
        while (current != null) {
            int comparison = item.compareTo(current.item);
            if (comparison == 0) {
                return current.item;
            }
            current = comparison < 0 ? current.left : current.right;
        }
        return null;
    }

    /**
     * Reemplaza el elemento equivalente y devuelve si fue encontrado.
     *
     * @param item elemento utilizado como clave de búsqueda
     * @param nuevo elemento que reemplazará al encontrado
     * @return {@code true} si se modificó un elemento; {@code false} si no se encontró
     */
    public boolean modificar(ELEMENT item, ELEMENT nuevo) {
        BTNode<ELEMENT> current = this.root;
        while (current != null) {
            int comparison = item.compareTo(current.item);
            if (comparison == 0) {
                current.item = nuevo;
                return true;
            }
            current = comparison < 0 ? current.left : current.right;
        }
        return false;
    }

    /**
     * Elimina y devuelve un elemento equivalente del árbol.
     *
     * @param item elemento utilizado como clave de búsqueda
     * @return elemento eliminado
     * @throws RuntimeException si el elemento no existe o el árbol está vacío
     */
    public ELEMENT remove(ELEMENT item) {
        return removeByCopy(item);
        //return removeByFusion(item);
    }

    private ELEMENT removeByCopy(ELEMENT item) {
        BTNode<ELEMENT> find = this.root;
        BTNode<ELEMENT> prev = null;
        while ((find != null) && (find.item.compareTo(item) != 0)) {
            prev = find;
            if (item.compareTo(find.item) < 0) {
                find = find.left;
            } else {
                find = find.right;
            }
        }
        if (find == null) {
            throw new RuntimeException("No existe el elemento o el árbol está vacío");
        } // find es el nodo con el valor a extraer y prev el padre de ese nodo
        ELEMENT save = find.item;
        BTNode<ELEMENT> node = find;
        if (node.right == null) { // no hay subarbol derecho
            node = node.left; // nodo con un descendiente u hoja
        } else {
            if (node.left == null) { // no hay subarbol izquierdo
                node = node.right; // nodo con un descendiente u hoja
            } else { // dos descendientes
                BTNode<ELEMENT> last = node;
                BTNode<ELEMENT> temp = node.right; // a la derecha (mayores)
                while (temp.left != null) { // busca a la izquierda el menor
                    last = temp;
                    temp = temp.left;
                }
                // temp es el menor de los mayores
                node.item = temp.item; // hace la copia
                if (last == node) {
                    last.right = temp.right;
                } else {
                    last.left = temp.right;
                }
                temp.right = null;
            }
        }
        // reajustar el arbol
        if (find == this.root) {
            this.root = node;
        } else {
            if (prev.left == find) {
                prev.left = node;
            } else {
                prev.right = node;
            }
        }
        return save;
    }

    /**
     * Devuelve los elementos del camino formado por los hijos derechos.
     *
     * @return lista con la raíz y sus descendientes derechos
     */
    public SimpleLinkedList<ELEMENT> getRightNodes() {
        SimpleLinkedList<ELEMENT> rightNodes = new SimpleLinkedList<>();
        BTNode<ELEMENT> current = this.root;
        while (current != null) {
            rightNodes.addLast(current.item);
            current = current.right;
        }
        return rightNodes;
    }

}
