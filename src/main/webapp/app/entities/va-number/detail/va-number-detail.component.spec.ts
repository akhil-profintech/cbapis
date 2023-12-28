import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { VANumberDetailComponent } from './va-number-detail.component';

describe('VANumber Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VANumberDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: VANumberDetailComponent,
              resolve: { vANumber: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(VANumberDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load vANumber on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', VANumberDetailComponent);

      // THEN
      expect(instance.vANumber).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
