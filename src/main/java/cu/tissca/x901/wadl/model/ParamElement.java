package cu.tissca.x901.wadl.model;

import cu.tissca.x901.wadl.Visitor;

/**
 * @author ariel.viera@gmail.com (Ariel Viera)
 */
public class ParamElement implements WadlElement {
    @Override
    public void accept(Visitor visitor){
        try {
            visitor.visitParamDescriptor(this);
        } finally {
            visitor.endVisitParamDescriptor(this);
        }
    }
}
