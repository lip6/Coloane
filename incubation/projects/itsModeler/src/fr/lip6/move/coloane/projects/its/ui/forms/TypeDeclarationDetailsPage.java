/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.projects.its.ui.forms;

import fr.lip6.move.coloane.interfaces.formalism.IElementFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.projects.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.projects.its.TypeDeclaration;
import fr.lip6.move.coloane.projects.its.plugin.editors.MultiPageEditor;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * A type declaration detail page.
 * @author Yann
 */
public final class TypeDeclarationDetailsPage extends ITSDetailsPage<TypeDeclaration> {
	private Text typeNametf;
	private Text typeFormalismtf;
	private Text typeFiletf;
	private TableViewer lviewer;
	private MultiPageEditor mpe;

	/**
	 * Ctor. pass master for openEditor button action.
	 * @param master the master page
	 */
	public TypeDeclarationDetailsPage(MultiPageEditor master) {
		this.mpe = master;
	}
	/**
	 * {@inheritDoc}
	 * @see org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();
		GridData gd;
//		layout.topMargin = 5;
//		layout.leftMargin = 5;
//		layout.rightMargin = 2;
//		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = getToolkit();
		Section s1 = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR);
		s1.marginWidth = 4;
		s1.marginHeight = 4;
		s1.setText("Type Definition Details"); //$NON-NLS-1$
		// s1.setDescription("Details .");
		//		s1.setDescription(Messages.getString("TypeOneDetailsPage.name")); //$NON-NLS-1$
		TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		s1.setLayoutData(td);
		Composite client = toolkit.createComposite(s1, SWT.WRAP);
		GridLayout glayout = new GridLayout();
		glayout.marginWidth = 10;
		glayout.marginHeight = 5;
		glayout.numColumns = 2;
		client.setLayout(glayout);

		
		toolkit.createLabel(client, "Type Name"); //$NON-NLS-1$
		typeNametf = toolkit.createText(client, "", SWT.SINGLE | SWT.BORDER); //$NON-NLS-1$
		typeNametf.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				if (getInput() != null) {
					getInput().setTypeName(typeNametf.getText());
				}
			}
		});
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		typeNametf.setLayoutData(gd);

		toolkit.createLabel(client, "Model File"); //$NON-NLS-1$
		typeFiletf = toolkit.createText(client, "", SWT.SINGLE ); //$NON-NLS-1$
		typeFiletf.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		typeFiletf.setLayoutData(gd);

		toolkit.createLabel(client, "Formalism"); //$NON-NLS-1$
		typeFormalismtf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		typeFormalismtf.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		typeFormalismtf.setLayoutData(gd);


		createSpacer(toolkit, client, 2);

		toolkit.createLabel(client, "Interface"); //$NON-NLS-1$
		Table requiredTable = toolkit.createTable(client, SWT.SINGLE);
		gd = new GridData(GridData.FILL_BOTH);
		requiredTable.setLayoutData(gd);
		lviewer = new TableViewer(requiredTable);
		lviewer.setContentProvider(new OfferedConceptsProvider());
		lviewer.setInput(getInput());

		Button oeb = toolkit.createButton(client, "Open Editor", SWT.PUSH);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		oeb.setLayoutData(gd);
		oeb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				mpe.openEditor(getInput());
			}
		});

		//		flatTypeButton.addSelectionListener(new SelectionAdapter() {
		//			@Override
		//			public void widgetSelected(SelectionEvent event) {
		//				ModelFlattener mf = new ModelFlattener();
		//				try {
		//					mf.doFlatten((CompositeTypeDeclaration) currentSelectedTypeDecl);
		//				} catch (Exception e) {
		//					e.printStackTrace();
		//				}
		//				try {
		//					FileDialog fileDialog = new FileDialog(getSite().getShell(), SWT.SAVE);
		//					// fontDialog.setFontList(text.getFont().getFontData());
		//					String filePath = fileDialog.open();
		//					if (filePath == null) {
		//						ErrorDialog.openError(getSite().getShell(),
		//								"Cancelled by user",
		//								"Add new type operation cancelled by user",
		//								new Status(1, "fr.lip6.move.coloane.its", "Cancel Dialog"));
		//					}
		//					IPath path = new Path(filePath);
		//					IFile outputff = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
		//					IGraph flatModel = mf.getFlatModel();
		//					GraphLayout.layout(flatModel);
		//					outputff.create(new ByteArrayInputStream(ModelWriter.translateToXML(flatModel).getBytes()), 0, null);
		//					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		//					try {
		//						if (outputff.exists()) {
		//							IDE.openEditor(page, outputff);
		//						}
		//					} catch (PartInitException e) {
		//						e.printStackTrace();
		//					}
		//				} catch (Exception e) {
		//					e.printStackTrace();
		//				}
		//			}
		//		});

		
		
		toolkit.paintBordersFor(s1);
		toolkit.paintBordersFor(client);
		s1.setClient(client);
	}
	
	/**
	 * Provider class to display the interface of a type declaration
	 * @author Yann
	 *
	 */
	class OfferedConceptsProvider implements IStructuredContentProvider {

		/**
		 * {@inheritDoc}
		 */
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof CompositeTypeDeclaration) {
				TypeDeclaration td = (TypeDeclaration) inputElement;
				String[] items = td.getLabels().toArray(new String[td.getLabels().size()]);
				Arrays.sort(items);
				return items;
			} else if (inputElement instanceof TypeDeclaration) {
				TypeDeclaration td = (TypeDeclaration) inputElement;
				IGraph graph = td.getGraph();
				IGraphFormalism formalism = graph.getFormalism().getRootGraph();
				IElementFormalism trans = formalism.getElementFormalism("transition");
				Collection<INode> nodes = graph.getNodes();

				List<String> toret = new ArrayList<String>();
				for (INode node : nodes) {
					if (node.getNodeFormalism().equals(trans)) {
						if (node.getAttribute("visibility").getValue().equals("public")) {
							toret.add(node.getAttribute("label").getValue() + " [ "
									+ node.getAttribute("earliestFiringTime").getValue() + ", "
									+ node.getAttribute("latestFiringTime").getValue() + " ]");
						}
					}
				}
				String[] items = toret.toArray(new String[toret.size()]);
				Arrays.sort(items);
				return items;
			}
			return new String[0];
		}

		/**
		 * {@inheritDoc}
		 */
		public void dispose() {
		}

		/**
		 * {@inheritDoc}
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

	}


	/**
	 * Update the state of the viewers
	 */
	protected void update() {
		TypeDeclaration input = getInput();

		// CHECKSTYLE OFF
		typeNametf.setText(input != null && input.getTypeName() != null ? input.getTypeName() : ""); //$NON-NLS-1$
		typeFormalismtf.setText(input != null && input.getTypeType() != null ? input.getTypeType() : "");
		typeFiletf.setText(input != null && input.getTypePath() != null ? input.getTypePath() : "");
		// CHECKSTYLE ON
		lviewer.setInput(input);
	}
}
