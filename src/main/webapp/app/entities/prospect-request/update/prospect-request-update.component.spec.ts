import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ProspectRequestService } from '../service/prospect-request.service';
import { IProspectRequest } from '../prospect-request.model';
import { ProspectRequestFormService } from './prospect-request-form.service';

import { ProspectRequestUpdateComponent } from './prospect-request-update.component';

describe('ProspectRequest Management Update Component', () => {
  let comp: ProspectRequestUpdateComponent;
  let fixture: ComponentFixture<ProspectRequestUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let prospectRequestFormService: ProspectRequestFormService;
  let prospectRequestService: ProspectRequestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ProspectRequestUpdateComponent],
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
      .overrideTemplate(ProspectRequestUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ProspectRequestUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    prospectRequestFormService = TestBed.inject(ProspectRequestFormService);
    prospectRequestService = TestBed.inject(ProspectRequestService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const prospectRequest: IProspectRequest = { id: 456 };

      activatedRoute.data = of({ prospectRequest });
      comp.ngOnInit();

      expect(comp.prospectRequest).toEqual(prospectRequest);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProspectRequest>>();
      const prospectRequest = { id: 123 };
      jest.spyOn(prospectRequestFormService, 'getProspectRequest').mockReturnValue(prospectRequest);
      jest.spyOn(prospectRequestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ prospectRequest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: prospectRequest }));
      saveSubject.complete();

      // THEN
      expect(prospectRequestFormService.getProspectRequest).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(prospectRequestService.update).toHaveBeenCalledWith(expect.objectContaining(prospectRequest));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProspectRequest>>();
      const prospectRequest = { id: 123 };
      jest.spyOn(prospectRequestFormService, 'getProspectRequest').mockReturnValue({ id: null });
      jest.spyOn(prospectRequestService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ prospectRequest: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: prospectRequest }));
      saveSubject.complete();

      // THEN
      expect(prospectRequestFormService.getProspectRequest).toHaveBeenCalled();
      expect(prospectRequestService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IProspectRequest>>();
      const prospectRequest = { id: 123 };
      jest.spyOn(prospectRequestService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ prospectRequest });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(prospectRequestService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
