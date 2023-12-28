import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { IAction } from 'app/entities/action/action.model';
import { ActionService } from 'app/entities/action/service/action.service';
import { ContextService } from '../service/context.service';
import { IContext } from '../context.model';
import { ContextFormService } from './context-form.service';

import { ContextUpdateComponent } from './context-update.component';

describe('Context Management Update Component', () => {
  let comp: ContextUpdateComponent;
  let fixture: ComponentFixture<ContextUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let contextFormService: ContextFormService;
  let contextService: ContextService;
  let actionService: ActionService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ContextUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ContextUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ContextUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    contextFormService = TestBed.inject(ContextFormService);
    contextService = TestBed.inject(ContextService);
    actionService = TestBed.inject(ActionService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Action query and add missing value', () => {
      const context: IContext = { id: 456 };
      const action: IAction = { id: 32505 };
      context.action = action;

      const actionCollection: IAction[] = [{ id: 21280 }];
      jest.spyOn(actionService, 'query').mockReturnValue(of(new HttpResponse({ body: actionCollection })));
      const additionalActions = [action];
      const expectedCollection: IAction[] = [...additionalActions, ...actionCollection];
      jest.spyOn(actionService, 'addActionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ context });
      comp.ngOnInit();

      expect(actionService.query).toHaveBeenCalled();
      expect(actionService.addActionToCollectionIfMissing).toHaveBeenCalledWith(
        actionCollection,
        ...additionalActions.map(expect.objectContaining),
      );
      expect(comp.actionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const context: IContext = { id: 456 };
      const action: IAction = { id: 20417 };
      context.action = action;

      activatedRoute.data = of({ context });
      comp.ngOnInit();

      expect(comp.actionsSharedCollection).toContain(action);
      expect(comp.context).toEqual(context);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IContext>>();
      const context = { id: 123 };
      jest.spyOn(contextFormService, 'getContext').mockReturnValue(context);
      jest.spyOn(contextService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ context });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: context }));
      saveSubject.complete();

      // THEN
      expect(contextFormService.getContext).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(contextService.update).toHaveBeenCalledWith(expect.objectContaining(context));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IContext>>();
      const context = { id: 123 };
      jest.spyOn(contextFormService, 'getContext').mockReturnValue({ id: null });
      jest.spyOn(contextService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ context: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: context }));
      saveSubject.complete();

      // THEN
      expect(contextFormService.getContext).toHaveBeenCalled();
      expect(contextService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IContext>>();
      const context = { id: 123 };
      jest.spyOn(contextService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ context });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(contextService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareAction', () => {
      it('Should forward to actionService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(actionService, 'compareAction');
        comp.compareAction(entity, entity2);
        expect(actionService.compareAction).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
