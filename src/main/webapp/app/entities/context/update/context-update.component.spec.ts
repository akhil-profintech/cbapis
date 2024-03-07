import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

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

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const context: IContext = { id: 456 };

      activatedRoute.data = of({ context });
      comp.ngOnInit();

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
});
