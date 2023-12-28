import { TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness, RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ContextDetailComponent } from './context-detail.component';

describe('Context Management Detail Component', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContextDetailComponent, RouterTestingModule.withRoutes([], { bindToComponentInputs: true })],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              component: ContextDetailComponent,
              resolve: { context: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(ContextDetailComponent, '')
      .compileComponents();
  });

  describe('OnInit', () => {
    it('Should load context on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', ContextDetailComponent);

      // THEN
      expect(instance.context).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
