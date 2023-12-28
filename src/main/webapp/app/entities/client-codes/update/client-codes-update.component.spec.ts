import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ClientCodesService } from '../service/client-codes.service';
import { IClientCodes } from '../client-codes.model';
import { ClientCodesFormService } from './client-codes-form.service';

import { ClientCodesUpdateComponent } from './client-codes-update.component';

describe('ClientCodes Management Update Component', () => {
  let comp: ClientCodesUpdateComponent;
  let fixture: ComponentFixture<ClientCodesUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let clientCodesFormService: ClientCodesFormService;
  let clientCodesService: ClientCodesService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([]), ClientCodesUpdateComponent],
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
      .overrideTemplate(ClientCodesUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ClientCodesUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    clientCodesFormService = TestBed.inject(ClientCodesFormService);
    clientCodesService = TestBed.inject(ClientCodesService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const clientCodes: IClientCodes = { id: 456 };

      activatedRoute.data = of({ clientCodes });
      comp.ngOnInit();

      expect(comp.clientCodes).toEqual(clientCodes);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientCodes>>();
      const clientCodes = { id: 123 };
      jest.spyOn(clientCodesFormService, 'getClientCodes').mockReturnValue(clientCodes);
      jest.spyOn(clientCodesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientCodes });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: clientCodes }));
      saveSubject.complete();

      // THEN
      expect(clientCodesFormService.getClientCodes).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(clientCodesService.update).toHaveBeenCalledWith(expect.objectContaining(clientCodes));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientCodes>>();
      const clientCodes = { id: 123 };
      jest.spyOn(clientCodesFormService, 'getClientCodes').mockReturnValue({ id: null });
      jest.spyOn(clientCodesService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientCodes: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: clientCodes }));
      saveSubject.complete();

      // THEN
      expect(clientCodesFormService.getClientCodes).toHaveBeenCalled();
      expect(clientCodesService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IClientCodes>>();
      const clientCodes = { id: 123 };
      jest.spyOn(clientCodesService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ clientCodes });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(clientCodesService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
